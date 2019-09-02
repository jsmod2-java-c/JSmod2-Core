/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core.plugin;

import cn.jsmod2.core.Server;
import cn.jsmod2.core.annotations.EnableRegister;
import cn.jsmod2.core.annotations.LoadBefore;
import cn.jsmod2.core.annotations.Main;
import cn.jsmod2.core.command.Command;
import cn.jsmod2.core.command.NativeCommand;
import cn.jsmod2.core.event.Listener;
import cn.jsmod2.core.ex.MainClassErrorException;
import cn.jsmod2.core.ex.PluginException;
import cn.jsmod2.core.interapi.IServer;
import cn.jsmod2.core.interapi.plugin.IPluginClassLoader;
import cn.jsmod2.core.log.ILogger;
import cn.jsmod2.core.utils.PluginFileVO;
import cn.jsmod2.core.utils.Utils;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/**
 * 用于加载插件的主类和配置信息
 *
 * @author magiclu550
 */

public class PluginClassLoader implements IPluginClassLoader {

    public static final String JSMOD2_PACKAGE = "cn.jsmod2";

    private Map<String,String> plugin_info = new HashMap<>();//文件名 介绍

    private Map<String,String> plugin_name = new HashMap<>();//文件名 插件名

    private Map<String,String> name_file = new HashMap<>();//插件名 文件名

    private Map<String,Plugin> name_plugins = new HashMap<>();

    private PluginManager manager;

    private File[] files;

    private List<Plugin> plugins = new ArrayList<>();

    private static PluginClassLoader classLoader;

    static {
        classLoader = new PluginClassLoader();
    }

    //文件名 插件名
    public Map<String, String> getName_file() {
        return name_file;
    }

    public Map<String, String> getPlugin_name() {
        return plugin_name;
    }

    private List<File> jarFiles = new ArrayList<>();

    void setManager(PluginManager manager){
        this.manager = manager;
    }

    public List<Plugin> loadPlugins(File pluginDir){
        files = pluginDir.listFiles();//all jar files
        loadFiles(files);
        for(File jar:jarFiles){
            plugins.add(loadPlugin(jar));
        }
        return plugins;
    }

    public void loadPlugin(String file){
        loadPlugin(new File(file));
    }

    //可以加载指定名字的插件
    private Plugin loadPlugin(File jar,String[] pName){
        if(!jar.getName().endsWith(".jar"))return null;
        if(plugin_name.containsKey(jar.getName())){
            return name_plugins.get(plugin_name.get(jar.getName()));
        }
        try{
            URL url = jar.toURI().toURL();

            URLClassLoader classLoader = new URLClassLoader(new URL[]{url},this.getClass().getClassLoader());

            JarFile file = new JarFile(jar);

            ZipEntry entry = file.getEntry("plugin.yml");

            if(entry!=null) {

                InputStream in = file.getInputStream(entry);

                PluginFileVO vo = ConfigGetter.getConfigGetter().toDoPluginSet(in);

                for (Plugin plu : plugins) {
                    if (plu.getPluginName().equals(vo.getPluginName())) {
                        plugins.remove(plu);
                    }
                }
                Object plugin;
                if(!vo.getMain_class().startsWith(JSMOD2_PACKAGE)) {
                    plugin_info.put(jar.getName(),vo.getDescription());
                    plugin = classLoader.loadClass(vo.getMain_class()).newInstance();
                }else{
                    throw new PluginException("the main class could not start with 'cn.jsmod2'");
                }

                return loadPluginInfo(plugin,vo,file,classLoader);


            }else{
                Enumeration<JarEntry> entries = file.entries();
                while (entries.hasMoreElements()){
                    JarEntry entry1 = entries.nextElement();
                    String name = entry1.getName();
                    if(name.endsWith(".class")){
                        String mainName = name.substring(0,name.lastIndexOf(".")).replace("/",".");
                        if(mainName.startsWith(JSMOD2_PACKAGE)) {
                            Utils.getMessageSender().error("the main class could not start with 'cn.jsmod2'");
                            continue;
                        }
                        Class<?> pluginClass = classLoader.loadClass(mainName);
                        Main main = pluginClass.getAnnotation(Main.class);
                        if(main!=null){
                            if(pName!=null&&!Arrays.asList(pName).contains(main.name())){
                                return null;
                            }
                            if(name_file.containsKey(main.name())){
                                return name_plugins.get(main.name());
                            }
                            plugin_info.put(jar.getName(),main.description());
                            name_file.put(main.name(),jar.getName());
                            plugin_name.put(jar.getName(),main.name());
                            PluginFileVO vo = new PluginFileVO(main.name(),pluginClass.getName(),main.description(),main.version());
                            Object obj = pluginClass.newInstance();
                            return loadPluginInfo(obj,vo,file,classLoader);
                        }
                    }
                }
            }

        }catch (Exception e){
            Utils.printException(e);
        }
        return null;
    }
    public Plugin loadPlugin(File jar) {

        return loadPlugin(jar,null);
    }

    public Map<String, String> getPlugin_info() {
        return plugin_info;
    }

    public void unloadPlugin(String name){
        Plugin removed = null;
        for(int i = 0;i<plugins.size();i++){
            if(plugins.get(i).getPluginName().equals(name)){
                removed = plugins.get(i);
                break;
            }
        }

        if(removed !=null) {
            removed.onDisable();
            plugins.remove(removed);
            plugin_info.remove(name_file.get(name));
            plugin_name.remove(name_file.get(name));
            name_file.remove(name);
        }else
            throw new PluginException("no such plugin");
    }

    public Plugin loadPluginInfo(Object plugin,PluginFileVO vo,JarFile jarFile,URLClassLoader loader) throws Exception{


        if (plugin instanceof PluginBase) {
            IServer server = Server.getSender().getServer();

            ILogger logger = server.getLogger();

            Plugin pluginObject = ((PluginBase) plugin);

            name_plugins.put(vo.getPluginName(),pluginObject);//放进去

            //检测如果有loadBefore

            LoadBefore before = pluginObject.getClass().getAnnotation(LoadBefore.class);
            if(before != null){
                //那么先判断loadBefore的内容
                String[] args = before.pluginName();
                for(File file:files){
                    //如果发现了名字,则先加载改名字的插件
                    loadPlugin(file,args);
                }
            }

            File dataFolder = new File(server.getPluginDir() + "/" + vo.getPluginName());
            if(!dataFolder.exists()){
                dataFolder.mkdirs();
            }
            pluginObject.init(logger, server, vo.getPluginName(), server.getServerFolder(), vo.getDescription(), this, dataFolder, vo.getVersion());

            pluginObject.onLoad();

            pluginObject.setEnabled(true);

            Server.getSender().getServer().getLogger().multiInfo(this.getClass(),"the plugin named:" + vo.getPluginName() + " is loading.. version: " + vo.getVersion(),"","");

            EnableRegister register = pluginObject.getClass().getAnnotation(EnableRegister.class);

            if(register!=null){


                List<Class<? extends Listener>> exclusionsListener = Arrays.asList(register.exclusionsListener());

                List<Class<? extends Command>> exclusionsCommand = Arrays.asList(register.exclusionsCommand());
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String name = entry.getName();
                    if(name.endsWith(".class")){
                        Class<?> clz = loader.loadClass(name.substring(0,name.lastIndexOf(".")).replace("/","."));
                        SpringContextUtil.loadBean(clz);
                        if(register.command()) {
                            if (mostSuperClass(clz).equals(NativeCommand.class) && !exclusionsCommand.contains(clz)) {
                                Object obj = clz.getConstructor(Plugin.class).newInstance(pluginObject);
                                manager.registerCommand((Command) obj);
                            }
                        }
                        if(register.listener()) {
                            if (Arrays.asList(mostSuperClass(clz).getInterfaces()).contains(Listener.class) && !exclusionsListener.contains(clz)) {
                                Object obj = clz.newInstance();
                                logger.multiInfo(this.getClass(),"loading listener","","");
                                manager.registerEvents((Listener) obj, pluginObject);
                            }
                        }
                    }
                }
                jarFile.close();

            }

            return pluginObject;
        } else {

            throw new MainClassErrorException("the main class must be the PluginBase's subclass");

        }
    }

    private Class<?> mostSuperClass(Class<?> clzz){
        while (true){
            Class<?> sub = clzz;
            clzz = clzz.getSuperclass();
            if(clzz.equals(Object.class))
                return sub;
        }
    }


    private void loadFiles(File[] files){
        if(files!=null){
            for(File file:files){
                if(file.getName().endsWith(".jar")){
                    jarFiles.add(file);
                }else{
                    loadFiles(file.listFiles());
                }
            }
        }
    }

    public static PluginClassLoader getClassLoader(){
        return classLoader;
    }

    public List<Plugin> getPlugins(){
        return plugins;
    }
}

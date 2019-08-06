/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core.plugin;

import cn.jsmod2.core.Server;
import cn.jsmod2.core.annotations.EnableRegister;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
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

    private PluginManager manager;

    private List<Plugin> plugins = new ArrayList<>();

    private static PluginClassLoader classLoader;

    static {
        classLoader = new PluginClassLoader();
    }

    private List<File> jarFiles = new ArrayList<>();

    void setManager(PluginManager manager){
        this.manager = manager;
    }

    public List<Plugin> loadPlugins(File pluginDir){
        File[] files = pluginDir.listFiles();//all jar files
        loadFiles(files);
        for(File jar:jarFiles){
            plugins.add(loadPlugin(jar));
        }
        return plugins;
    }

    public void loadPlugin(String file){
        loadPlugin(new File(file));
    }

    public Plugin loadPlugin(File jar) {
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
                            PluginFileVO vo = new PluginFileVO(main.name(),pluginClass.getName(),main.description(),main.version());
                            Object obj = pluginClass.newInstance();
                            return loadPluginInfo(obj,vo,file,classLoader);
                        }
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void unloadPlugin(String name){
        Plugin removed = null;
        for(int i = 0;i<plugins.size();i++){
            if(plugins.get(i).getPluginName().equals(name)){
                removed = plugins.get(i);
                break;
            }
        }
        if(removed !=null)
            plugins.remove(removed);
        else
            throw new PluginException("no such plugin");
    }

    public Plugin loadPluginInfo(Object plugin,PluginFileVO vo,JarFile jarFile,URLClassLoader loader) throws Exception{


        if (plugin instanceof PluginBase) {
            IServer server = Server.getSender().getServer();

            ILogger logger = server.getLogger();

            Plugin pluginObject = ((PluginBase) plugin);

            pluginObject.init(logger, server, vo.getPluginName(), server.getServerFolder(), vo.getDescription(), this, new File(server.getPluginDir() + "/" + vo.getPluginName()), vo.getVersion());

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

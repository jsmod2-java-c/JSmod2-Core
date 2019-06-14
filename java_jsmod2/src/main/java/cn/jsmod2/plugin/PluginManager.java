/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.plugin;


import cn.jsmod2.CommandSender;
import cn.jsmod2.Console;
import cn.jsmod2.Server;
import cn.jsmod2.annotations.NativeListener;
import cn.jsmod2.api.player.Player;
import cn.jsmod2.command.NativeCommand;
import cn.jsmod2.command.PowerPool;
import cn.jsmod2.event.Listener;
import cn.jsmod2.event.packet.ServerPacketEvent;
import cn.jsmod2.ex.EventException;
import cn.jsmod2.ex.NoSuchPluginNameException;
import cn.jsmod2.ex.PluginException;
import cn.jsmod2.ex.TypeErrorException;
import cn.jsmod2.script.Jsmod2Script;
import cn.jsmod2.utils.MethodInvokeMapper;
import cn.jsmod2.utils.Utils;
import cn.jsmod2.command.Command;
import cn.jsmod2.event.Event;
import cn.jsmod2.annotations.EventManager;
import cn.jsmod2.network.command.CommandRegisterPacket;

import java.lang.reflect.Method;
import java.util.*;


/**
 * the plugin manager
 *
 * @author magiclu550 #(code) jsmod2
 */

public class PluginManager {

    private Map<Listener, List<MethodInvokeMapper>> listenerMapper = new HashMap<>();

    private List<NativeCommand> commands = new ArrayList<>();

    private Server server;

    public PluginManager(Server server){
        this.server = server;
    }

    public PluginClassLoader getPluginClassLoader(){
        return PluginClassLoader.getClassLoader();
    }

    public Plugin getPlugin(String pluginName){
        List<Plugin> plugins = getPluginClassLoader().getPlugins();
        for(Plugin plugin:plugins){
            if(plugin.getPluginName().equals(pluginName)){
                return plugin;
            }
        }
        throw new NoSuchPluginNameException(pluginName);
    }


    public List<Plugin> getPlugins(){
        return getPluginClassLoader().getPlugins();
    }

    public void registerCommand(Command command){
        Plugin plugin = command.getPlugin();
        if(plugin.isEnabled()){
            commands.add(command);
            server.getCommandInfo().put(command.getCommandName(),command.getDescription());
            CommandRegisterPacket packet = new CommandRegisterPacket();
            packet.command = command;
            ServerPacketEvent event = new ServerPacketEvent(packet);
            callEvent(event);
            server.sendPacket(packet);//发包
        }else{
            throw new PluginException("the plugin is not enabled");
        }
    }

    public void plugins(){
        List<Plugin> plugins = getPlugins();
        int i = 0;
        Utils.getMessageSender().info("plugins["+plugins.size()+"]:");
        for(Plugin plugin:plugins){
            Utils.getMessageSender().normal(plugin.getPluginName(),":"+plugin.getVersion());
            if(i%5==0){
                Utils.getMessageSender().newLine();
            }
            i++;
        }
    }

    /**
     * registerEvents
     * @param listener
     */

    public void registerEvents(final Listener listener,Plugin plugin){
        if(plugin!=null){
            if(!plugin.isEnabled()){
                throw new PluginException("the plugin is not enabled");
            }
        }else{
            if(listener.getClass().getAnnotation(NativeListener.class)==null){
                throw new TypeErrorException("the listener type must be native type");
            }
        }
        Utils.TryCatch(()->{
            Class<?> type = listener.getClass();
            Listener listenerInstance = (Listener)type.newInstance();
            Method[] methods = type.getDeclaredMethods();
            for(Method method:methods) {
                addMethod(method,listenerInstance);
            }
        });
    }

    public void registerEvent(final Method method,Plugin plugin){
        if(!plugin.isEnabled()){
            throw new PluginException("the plugin is not enabled");
        }
        Utils.TryCatch(()->{
            Class<?> type = method.getDeclaringClass();
            addMethod(method,(Listener)(type.newInstance()));
        });
    }

    private void addMethod(Method method,Listener listenerInstance){
        EventManager manager = method.getDeclaredAnnotation(EventManager.class);
        if (manager != null) {
            MethodInvokeMapper mapper = new MethodInvokeMapper(manager.priority(), method);
            List<MethodInvokeMapper> methodMappers = listenerMapper.get(listenerInstance);
            if(methodMappers!=null){
                methodMappers.add(mapper);
                listenerMapper.put(listenerInstance,methodMappers);
            }else{
                methodMappers = new ArrayList<>();
                methodMappers.add(mapper);
                listenerMapper.put(listenerInstance,methodMappers);
            }
        }
    }

    public void callEvent(final Event event){
        Utils.TryCatch(()->{
            Set<Map.Entry<Listener,List<MethodInvokeMapper>>> set = listenerMapper.entrySet();
            for(Map.Entry<Listener,List<MethodInvokeMapper>> entry:set){
                Listener listener = entry.getKey();
                List<MethodInvokeMapper> methods = entry.getValue();
                List<MethodInvokeMapper> invoker = new ArrayList<>();
                for(MethodInvokeMapper mapper:methods){
                    Class[] classes = mapper.getMethod().getParameterTypes();
                    if(classes.length == 1){
                        if(classes[0].getName().equals(event.getEventName())){
                            invoker.add(mapper);
                        }
                    }else{
                        throw new EventException("the event method must have one parameter");
                    }
                }
                invoker.sort(Comparator.comparing(MethodInvokeMapper::getPriority));
                for(MethodInvokeMapper method:invoker){
                    method.getMethod().invoke(listener,event);
                }
            }
        });
    }


    public List<NativeCommand> getCommands(){
        return commands;
    }

    public List<NativeCommand> getPluginCommands(){
        List<NativeCommand> pluginCommands = new ArrayList<>();
        for(NativeCommand cmd:commands){
            if(cmd instanceof Command){
                pluginCommands.add(cmd);
            }
        }
        return pluginCommands;
    }

    public boolean executeCommand(String commandName, String[] args, CommandSender sender){
        for(NativeCommand command:commands){
            if(command.getCommandName().equals(commandName)){
                //指令发送者所拥有的权限是否包含指令允许的权限
                if(PowerPool.poolMapping().get(sender.getName()).contains(command.getPower())){
                    args = Jsmod2Script.setThat(args);
                    return command.execute(sender,args);
                }else{
                    Utils.getMessageSender().error("do not have this power");
                    if(sender instanceof Player){
                        ((Player) sender).personalBroadcast(1,"do not have "+command.getPower(),true);
                    }
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * 控制台执行指令
     *
     */
    public boolean consoleExecuteCommand(String command){
        return Console.getConsole().runConsoleCommand(command);
    }

    public List<Listener> getAllListener(){
        List<Listener> listeners = new LinkedList<>();
        Set<Map.Entry<Listener,List<MethodInvokeMapper>>> sets = listenerMapper.entrySet();
        for(Map.Entry<Listener,List<MethodInvokeMapper>> entry:sets){
            Listener listener = entry.getKey();
            if(listener.getClass().getDeclaredAnnotation(NativeListener.class)==null){
                listeners.add(listener);
            }
        }
        return listeners;
    }

    public void clear(){
        commands.removeAll(getPluginCommands());
        List<Listener> commonListeners = getAllListener();
        for(Listener listener:commonListeners){
            listenerMapper.remove(listener);
        }
        this.getPlugins().clear();
    }
}

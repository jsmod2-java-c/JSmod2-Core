package cn.jsmod2.command;

import cn.jsmod2.core.CommandSender;
import cn.jsmod2.core.Powers;
import cn.jsmod2.core.Server;
import cn.jsmod2.core.command.NativeCommand;
import cn.jsmod2.core.log.ServerLogger;
import cn.jsmod2.core.utils.Utils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


/**
 * jsmod2的中央服务器规范，可以连接指定的镜像服务器进行插件的下载
 * upload name version 对应插件名称/name-version.jar
 */
public class DownloadPluginCommand extends NativeCommand {

    public static final String MIRROR = "jsmod2.plugin.mirror";

    public DownloadPluginCommand() {
        super("download", Powers.CONSOLE,"upload the plugin");
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        if(strings.length < 2)return false;
        Server.getSender().getServer().getScheduler().executeRunnable(()->{
            String fileName = strings[0]+"-"+strings[1]+".jar";
            Utils.TryCatch(()->{
                URL url = new URL(Server.getSender().getServer().serverProp.getProperty(MIRROR)+"/"+fileName);
                URLConnection connection = url.openConnection();
                InputStream stream = connection.getInputStream();
                String pluginFile = Server.getSender().getServer().pluginDir+"/"+fileName;
                FileOutputStream file = new FileOutputStream(pluginFile);
                byte[] buffer = new byte[1204];
                int byteSum = 0;
                int byteRead;
                while ((byteRead = stream.read(buffer))!=-1){
                    ServerLogger.getLogger().multiInfo(getClass(),"downloading the plugins "+fileName+" into plugins ["+byteSum+"KB]/"+stream.available()+"KB","","");
                    file.write(buffer,0,byteRead);
                    byteSum+=byteRead;
                }
                ServerLogger.getLogger().multiInfo(getClass(),"download success!","","");
                if(strings.length == 3){
                    if(strings[2].equals("true")){
                        Server.getSender().getServer().getPluginManager().getPluginClassLoader().loadPlugin(pluginFile);
                    }
                }
                file.close();
                stream.close();
            });
        });
        return true;
    }
}

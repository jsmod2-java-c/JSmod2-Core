package cn.jsmod2.core.interapi;




import cn.jsmod2.core.*;
import cn.jsmod2.core.interapi.command.IOpsFile;
import cn.jsmod2.core.interapi.network.IRequester;
import cn.jsmod2.core.interapi.plugin.IPluginManager;
import cn.jsmod2.core.log.ILogger;
import cn.jsmod2.core.protocol.ControlPacket;
import cn.jsmod2.core.protocol.DataPacket;
import cn.jsmod2.core.protocol.GetPacket;
import cn.jsmod2.core.protocol.SetPacket;
import cn.jsmod2.core.utils.Future;

import java.io.Closeable;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.locks.Lock;

/**
 * IServer，关于jsmod2的插件api
 * @author Magiclu550 #(code) jsmod2
 */

public interface IServer extends Start,Closeable, Reloadable {

    void packetCommandManage(int id,String message) throws Exception;

    void registerPacketManger(List<Manager> managers);

    void help();

    Map<String, String> getCommandInfo();

    IPluginManager getPluginManager();

    void reload();

    GameServer getGameServer();

    IRequester getRequester(ControlPacket packet);

    List<RegisterTemplate> getRegisters();

    IOpsFile getOpsFile();

    double getTPS();

    long getStartTime();

    long getStartSuccessTime();

    Lock getLock();

    ILogger getLogger();

    Properties getLang();

    IServer getServer();

    File getServerFolder();

    String getPluginDir();

    File getServerfolder();

    Properties getServerProperties();

}

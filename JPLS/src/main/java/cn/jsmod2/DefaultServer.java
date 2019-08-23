package cn.jsmod2;

import cn.jsmod2.api.event.NativeJoinListener;
import cn.jsmod2.core.FileSystem;
import cn.jsmod2.core.Manager;
import cn.jsmod2.core.RegisterTemplate;
import cn.jsmod2.core.Server;
import cn.jsmod2.core.annotations.PacketCMD;
import cn.jsmod2.core.event.packet.ServerPacketEvent;
import cn.jsmod2.api.server.Smod2Server;
import cn.jsmod2.network.ServerInitPacket;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

public class DefaultServer extends Server {

    //初始化服务端的指令
    @PacketCMD
    @Deprecated
    private static final int INIT_COMMAND = 0x00;
    //关闭服务端的指令
    @PacketCMD private static final int CLOSE_COMMAND = 0x02;

    public DefaultServer() {
        super(new Smod2Server(),false);
        try {
            String about = FileSystem.getFileSystem().readInitPropertiesInfo().getProperty(Register.ABOUT);
            log.multiInfo(getClass(),"Running JSMod2 Version: "+about,"","");
        }catch (Exception e){
            log.multiError(getClass(),e.getMessage(),"","");
        }
    }

    @Override
    public void packetCommandManage(int id,String message) throws Exception{
        if(id == CLOSE_COMMAND){
            close();
        }

        //初始化服务器，第一个数据包

        //if(id == INIT_COMMAND){
        //    setServer(message);
        //}
    }

    @Deprecated
    private void setServer(String message) throws Exception{
        ServerInitPacket packet = new ServerInitPacket();
        ServerPacketEvent event = new ServerPacketEvent(packet);
        pluginManager.callEvent(event);
        gameServer.updateServer(packet.decode(message.getBytes(serverProp.getProperty("encode"))));
    }

    @Override
    public void registerPacketManger(List<Manager> managers) {
        managers.add(PacketManager.getManager());
    }


    @Override
    public void registerNativeEvents() {
        this.pluginManager.registerNativeEvents(new NativeJoinListener());
    }

    @Override
    public void registerTemplates(List<RegisterTemplate> registers,Server server) {
        registers.add(new Register());
    }




}

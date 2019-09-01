/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
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


import java.util.List;

/**
 * <P>
 * 本类是JSmod2的配置服务器，继承自core的Server类，在启动时，实例化该对象
 * 启用start启动全部线程,close关闭服务器.
 * </P>
 * <P>
 * 同时注册了PacketManager,默认监听器,和注册级.
 * </P>
 * <P>
 * 其他参见Server类的注释,同时可以通过Server来DIY服务器对象
 * </P>
 * @see cn.jsmod2.core.interapi.IServer
 * @see cn.jsmod2.core.Reloadable
 * @see cn.jsmod2.core.Server
 * @see cn.jsmod2.core.Start
 * @see java.io.Closeable
 * @see java.lang.AutoCloseable
 * @author MagicLu550
 * @since Jsmod2 1.0.0
 * @version 1.0.0
 */

public class DefaultServer extends Server {

    /**
     * <P>
     * 在初始版本中，通过发送整个对象来赋值，如今采用RPC方式实现数据的采集
     * 和数据的调用.
     * </P>
     * <P>
     * 在后来版本中，该方式被抛弃,因为服务器为单例模式的对象，通过双向交互来得到
     * 数据
     * </P>
     * @since 1.0.0
     * @deprecated
     */
    @PacketCMD
    @Deprecated
    private static final int INIT_COMMAND = 0x00;
    /**
     * <P>
     * 在服务器关闭时，会发送一个数据包命令，关闭现有的服务器进程
     * </P>
     * <P>
     * 在最新版本中，几乎处于抛弃状态，但仍然支持使用
     * </P>
     * @since 1.0.0
     */
    @PacketCMD private static final int CLOSE_COMMAND = 0x02;

    /**
     * <P>
     * 默认服务器对象的构造方法，必须是没有参数的，否则在Application.run的
     * 过程中，会发生实例化的异常，原因是没有默认的构造方法(<init>)
     * </P>
     * <P>
     * 构造方法调用的参数中,是单例的游戏端对象,使用TCP协议进行数据传输，防止数据包
     * 丢失的问题，所以采用该协议
     * </P>
     */
    public DefaultServer() {
        super(new Smod2Server(),false);
        try {
            String about = FileSystem.getFileSystem().readInitPropertiesInfo().getProperty(Register.ABOUT);
            log.multiInfo(getClass(),"Running JSMod2 Version: "+about,"","");
        }catch (Exception e){
            log.multiError(getClass(),e.getMessage(),"","");
        }
    }

    /**
     * <P>
     *  可以对拆分后的数据包进行处理，应用AOP的思想,主要的作用是对于刚开始的
     *  数据包进行处理，这里对于CLOSE_COMMAND进行了处理，可以通过该指令关闭
     *  服务器，但是如果应用RPC or RPC Heart-beat时，建议使用execute来执行
     *  stop命令，会更加方便
     * </P>
     * @param id 拆分所得的数据包id
     * @param message 拆分所得的json字符串
     */
    @Override
    public void packetCommandManage(int id,String message) {
        if(id == CLOSE_COMMAND){
            close();
        }

        //初始化服务器，第一个数据包

        //if(id == INIT_COMMAND){
        //    setServer(message);
        //}
    }

    /**
     * 原本作为在packetCommandManage中使用，来初始化游戏服务器，但最终的下场
     * 和INIT_COMMAND的下场一直，由于采用双向数据采集，所以不需要该方法,但为了
     * 今后的研究使用，故淘汰
     * @param message json字符串
     * @throws Exception 为了处理编码不存在的异常而整体抛出，事实上，不建议这种方式，
     *                  未来代码不建议使用这种方式
     */
    @Deprecated
    private void setServer(String message) throws Exception{
        ServerInitPacket packet = new ServerInitPacket();
        ServerPacketEvent event = new ServerPacketEvent(packet);
        pluginManager.callEvent(event);
        gameServer.updateServer(packet.decode(message.getBytes(serverProp.getProperty("encode"))));
    }

    /**
     * 作用是用于注册数据包处理对象，这里注册使用的是PacketManager,即默认的数据包
     * 处理器
     * @param managers 数据包处理链，会注册到Server的注册机中
     */
    @Override
    public void registerPacketManger(List<Manager> managers) {
        managers.add(PacketManager.getManager());
    }

    /**
     * 注册默认的本地监听器，本地监听器的唯一不同就是不受插件约束和永久存在，
     * 即不会被清理，并绑定在服务器上，同时必须拥有@NativeListener注解
     */
    @Override
    public void registerNativeEvents() {
        this.pluginManager.registerNativeEvents(new NativeJoinListener());
    }

    /**
     * 用于注册"注册机"对象，注册机对象中带有@RegisterMethod注解的方法会被调用,
     * 执行注册功能
     * @param registers 注册机链，会注册到Server的根注册机上
     * @param server 默认的server对象(一般为本对象'this')
     */
    @Override
    public void registerTemplates(List<RegisterTemplate> registers,Server server) {
        registers.add(new Register());
    }

    /**
     * 这里重写了close方法，如果存在rpc模式，那么就会关闭rpc模式，目的是防止发生僵尸
     * 进程，导致程序没有完全的关闭
     */
    @Override
    public void close() {
        if(ServerStarter.getInstance()!=null)
            ServerStarter.getInstance().getWebServer().shutdown();
        super.close();
    }
}

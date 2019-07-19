package cn.jsmod2.core.forplayer;



import cn.jsmod2.core.*;
import cn.jsmod2.core.annotations.RegisterMethod;

import java.util.HashSet;
import java.util.List;
import java.util.Set;



/**
 * 具体流程就是
 * 一个玩家发送了json请求
 *  如果type为create，那么创建一个玩家对象，并通知全部玩家创建了一个玩家对象(json)
 *  如果type为del，那么删除一个玩家对象，并通知全部玩家删除了一个玩家对象(json)
 *  如果type为其他类型，那么调用服务端返回的RegisterType对象，并通过Type注解得到方法。
 *  首先传入entity，紧接着根据Param注解将值注入到方法，并运行(Type方法继承中，必须调用
 *  一次和send有关的方法，以将数据处理后，根据自定义的发送发送出去),entity对象为当前发送json请求的玩家，
 *  send方法可以通知全部玩家消息，entity对象中存在entries，里面有全部json信息，自定义可将信息重组发送
 *  见forPlayer里的ExamplePlayer
 */

public abstract class PlayerServer extends Server {



    //一个存储玩家的阻塞队列
    //当玩家人数超过阻塞限度
    //向玩家记录的ip:port发json
    protected final Set<PlayerEntity> players;

    public static final String MAX_PLAYER = "player.max";

    public static final String DECODE_BY = "server.decode.by";


    public PlayerServer() {
        super(null,true);
        this.players = new HashSet<>(Integer.parseInt(FileSystem.getFileSystem().serverProperties(this).getProperty(MAX_PLAYER)));
        this.appProps = FileSystem.getFileSystem().getApplicationInfo();
    }


    @Override
    public void registerTemplates(List<RegisterTemplate> registers, Server server) {
        registers.add(new DefaultRegister());
    }

    @Override
    public void registerPacketManger(List<Manager> managers) {
        managers.add(new PlayerPacketManager(this,appProps,registerType()));
    }

    class DefaultRegister extends RegisterTemplate {
        @Override
        @RegisterMethod
        public void registerServerProperties() {
            serverProperties.put(MAX_PLAYER,"10");
        }
    }
    //这里返回一个RegisterType的子类对象
    public abstract RegisterType registerType();



}

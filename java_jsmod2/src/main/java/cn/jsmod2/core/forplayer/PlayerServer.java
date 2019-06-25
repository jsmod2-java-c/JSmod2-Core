package cn.jsmod2.core.forplayer;



import cn.jsmod2.core.*;
import cn.jsmod2.core.annotations.RegisterMethod;

import java.util.HashSet;
import java.util.List;
import java.util.Set;



/**
 * 采用nio处理玩家的请求
 */

public abstract class PlayerServer extends Server {



    //一个存储玩家的阻塞队列
    //当玩家人数超过阻塞限度
    //向玩家记录的ip:port发json
    protected Set<PlayerEntity> players;

    public static final String MAX_PLAYER = "player.max";

    public static final String DECODE_BY = "server.decode.by";

    public PlayerServer() {
        super(null);
        this.players = new HashSet<>(Integer.parseInt(FileSystem.getFileSystem().serverProperties(this).getProperty(MAX_PLAYER)));
        this.appProps = FileSystem.getFileSystem().getApplicationInfo();
    }


    @Override
    public void registerTemplates(List<RegisterTemplate> registers, Server server) {
        registers.add(new DefaultRegister());
    }

    @Override
    public void registerPacketManger(List<Manager> managers) {
        managers.add(new PlayerPacketManager(this,appProps));
    }

    class DefaultRegister extends RegisterTemplate {
        @Override
        @RegisterMethod
        public void registerServerProperties() {
            serverProperties.put(MAX_PLAYER,"10");
        }
    }

}

package cn.jsmod2.core;


import cn.jsmod2.api.map.IMap;
import cn.jsmod2.api.server.IRound;

import java.util.List;

/**
 * 用于封装其他语言服务器信息，如果要将该服务器做成与其他服务器交互的话
 *
 * @author magiclu550
 */

public interface GameServer {

    List<? extends ISimplePlayer> getPlayers();

    @Deprecated
    GameServer updateServer(GameServer server);

    default int getPort() {
        return 0;
    }


    default String getIpAddress() {
        return "";
    }


    default IRound getRound() {
        return null;
    }

    default IMap getMap() {
        return null;
    }


    default int getNumPlayers() {
        return 0;
    }


    default int getMaxPlayers() {
        return 0;
    }

    default void setMaxPlayers(int maxPlayers) {
    }

}

package cn.jsmod2.core;


import java.util.List;

/**
 * 用于封装其他语言服务器信息，如果要将该服务器做成与其他服务器交互的话
 *
 * @author magiclu550
 */

public interface GameServer {

    List<? extends ISimplePlayer> getPlayers();

    GameServer updateServer(GameServer server);
}

package cn.jsmod2.core;

import cn.jsmod2.api.player.Player;

import java.util.List;

public interface GameServer {

    List<Player> getPlayers();

    GameServer updateServer(GameServer server);
}

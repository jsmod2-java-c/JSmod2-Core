package cn.jsmod2.scpslserver.network.command;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.entity.Player;

public class PlayerVO extends CommandVO{

    private Player player;

    public Player getPlayer() {
        return player;
    }
    @UseForServerInit
    public void setPlayer(Player player) {
        this.player = player;
    }
}

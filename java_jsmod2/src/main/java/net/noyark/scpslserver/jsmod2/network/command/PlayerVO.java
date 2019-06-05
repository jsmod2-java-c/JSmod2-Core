package net.noyark.scpslserver.jsmod2.network.command;

import net.noyark.scpslserver.jsmod2.annotations.UseForServerInit;
import net.noyark.scpslserver.jsmod2.entity.Player;

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

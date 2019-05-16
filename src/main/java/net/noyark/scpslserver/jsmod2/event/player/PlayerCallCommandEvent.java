package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;

public class PlayerCallCommandEvent extends PlayerEvent {
    private String returnMessage;
    private String command;

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public String getCommand() {
        return command;
    }

    public PlayerCallCommandEvent(Player player, String returnMessage, String command) {
        super(player);
        this.returnMessage = returnMessage;
        this.command = command;
    }
}

package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.entity.Player;

/**
 * @author kevinj
 */

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

    /** java-bean */
    @UseForServerInit
    public void setCommand(String command) {
        this.command = command;
    }

    public PlayerCallCommandEvent(){

    }

    public PlayerCallCommandEvent(Player player, String returnMessage, String command) {
        super(player);
        this.returnMessage = returnMessage;
        this.command = command;
    }
}

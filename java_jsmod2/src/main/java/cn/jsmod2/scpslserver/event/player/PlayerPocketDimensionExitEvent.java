package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.entity.Player;

import java.util.Vector;
/**
 * @author kevinj
 */
public class PlayerPocketDimensionExitEvent extends PlayerEvent {
    private Vector exitPosition;

    public PlayerPocketDimensionExitEvent(Player player, Vector exitPosition) {
        super(player);
        this.exitPosition = exitPosition;
    }

    public PlayerPocketDimensionExitEvent(){

    }

    public Vector getExitPosition() {
        return exitPosition;
    }

    public void setExitPosition(Vector exitPosition) {
        this.exitPosition = exitPosition;
    }
}

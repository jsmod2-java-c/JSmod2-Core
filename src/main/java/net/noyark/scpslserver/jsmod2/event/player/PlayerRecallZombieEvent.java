package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;
/**
 * @author kevinj
 */
public class PlayerRecallZombieEvent extends PlayerEvent{
    private Player target;

    private boolean allowRecall;

    public PlayerRecallZombieEvent(Player player, Player target, boolean allowRecall) {
        super(player);
        this.target = target;
        this.allowRecall = allowRecall;
    }

    public PlayerRecallZombieEvent(){

    }

    public boolean isAllowRecall() {
        return allowRecall;
    }

    public void setAllowRecall(boolean allowRecall) {
        this.allowRecall = allowRecall;
    }

    public Player getTarget() {
        return target;
    }
    /** java-bean */
    public void setTarget(Player target) {
        this.target = target;
    }


}

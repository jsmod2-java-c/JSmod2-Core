/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.event.player;

import cn.jsmod2.annotations.UseForServerInit;
import cn.jsmod2.api.player.Player;
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
    @UseForServerInit
    public void setTarget(Player target) {
        this.target = target;
    }


}

/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.event.environment;

import cn.jsmod2.api.player.Player;


/**
 * @author Kevinj
 * @author magiclu550
 */

public class WarheadStartEvent extends WarheadEvent {
    private boolean isResumed;
    private boolean openDoorsAfter;

    public WarheadStartEvent(Player player, float timeLeft) {
        super(player, timeLeft);
    }

    public boolean isResumed() {
        return isResumed;
    }

    public void setResumed(boolean resumed) {
        isResumed = resumed;
    }

    public boolean isOpenDoorsAfter() {
        return openDoorsAfter;
    }

    public void setOpenDoorsAfter(boolean openDoorsAfter) {
        this.openDoorsAfter = openDoorsAfter;
    }

    public WarheadStartEvent(){

    }
}

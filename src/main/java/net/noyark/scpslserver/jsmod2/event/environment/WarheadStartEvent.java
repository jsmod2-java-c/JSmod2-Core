package net.noyark.scpslserver.jsmod2.event.environment;

import net.noyark.scpslserver.jsmod2.entity.Player;


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

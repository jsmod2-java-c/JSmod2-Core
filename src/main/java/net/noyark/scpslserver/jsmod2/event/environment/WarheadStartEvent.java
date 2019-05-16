package net.noyark.scpslserver.jsmod2.event.environment;

import net.noyark.scpslserver.jsmod2.entity.Player;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;

import java.awt.event.InputEvent;

/**
 * @author Kevinj
 * @author magiclu550
 */

public class WarheadStartEvent extends WarheadEvent {
    public boolean isResumed;
    public boolean openDoorsAfter;

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

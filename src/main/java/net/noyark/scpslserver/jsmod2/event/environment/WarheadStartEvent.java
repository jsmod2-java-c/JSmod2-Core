package net.noyark.scpslserver.jsmod2.event.environment;

import net.noyark.scpslserver.jsmod2.entity.Player;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;

import java.awt.event.InputEvent;

/**
 * @author Kevinj
 */

public class WarheadStartEvent extends WarheadEvent {
    public boolean IsResumed;
    public boolean OpenDoorsAfter;

    public WarheadStartEvent(Player player, float timeLeft) {
        super(player, timeLeft);
    }

    public boolean isResumed() {
        return IsResumed;
    }

    public void setResumed(boolean resumed) {
        IsResumed = resumed;
    }

    public boolean isOpenDoorsAfter() {
        return OpenDoorsAfter;
    }

    public void setOpenDoorsAfter(boolean openDoorsAfter) {
        OpenDoorsAfter = openDoorsAfter;
    }

}

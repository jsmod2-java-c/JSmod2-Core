package net.noyark.scpslserver.jsmod2.event.environment;

import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.event.Event;

public class ScpDeathAnnouncementEvent extends Event {
    public boolean ShouldPlay;
    public Player DeadPlayer;
    public Role PlayerRole;

    public boolean isShouldPlay() {
        return ShouldPlay;
    }

    public void setShouldPlay(boolean shouldPlay) {
        ShouldPlay = shouldPlay;
    }

    public Player getDeadPlayer() {
        return DeadPlayer;
    }

    public Role getPlayerRole() {
        return PlayerRole;
    }

    public ScpDeathAnnouncementEvent(boolean shouldPlay, Player deadPlayer, Role playerRole) {
        ShouldPlay = shouldPlay;
        DeadPlayer = deadPlayer;
        PlayerRole = playerRole;
    }


}

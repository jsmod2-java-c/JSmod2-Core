package net.noyark.scpslserver.jsmod2.event.admin;

import net.noyark.scpslserver.jsmod2.utils.AuthType;
import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.event.Event;


/**
 * @author magiclu550 #(code) jsmod2
 */

public class AuthCheckEvent extends Event {

    private Player requester;

    private AuthType type;

    private boolean allow;

    private boolean successful;

    public Player getRequester() {
        return requester;
    }

    public void setRequester(Player requester) {
        this.requester = requester;
    }

    public AuthType getType() {
        return type;
    }

    public void setType(AuthType type) {
        this.type = type;
    }

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}

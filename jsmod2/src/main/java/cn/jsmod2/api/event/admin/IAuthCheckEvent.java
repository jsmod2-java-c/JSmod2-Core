package cn.jsmod2.api.event.admin;

import cn.jsmod2.api.player.IPlayer;
import cn.jsmod2.api.server.AuthType;
import cn.jsmod2.core.interapi.event.IEvent;

/**
 * Called when authentication is checked for.
 * @author magiclu550
 * @see cn.jsmod2.core.interapi.event.IEvent
 * @since smod2 3.4.0 - 3.5.0
 */
public interface IAuthCheckEvent extends IEvent {

    /**
     * @return The player who requests authentication
     */
    IPlayer getRequester();

    /**
     * @return The type of authentication.
     */

    AuthType getType();

    /**
     * Set the type of authentication.
      * @param type
     */
    void setType(AuthType type);

    /**
     * @return Was the authentication allowed?
     */
    boolean isAllow();

    /**
     * Set the authentication allowed.
     * @param allow the allowed
     */
    void setAllow(boolean allow);

    /**
     *
     * @return The message given if authentication was denied
     */
    String getDeniedMessage();


    /**
     * The message given if authentication was denied
     * @param message the denied message
     */
    void setDeniedMessage(String message);

    /**
     * The player who requests authentication
     * @param requester the requester
     */
    void setRequester(IPlayer requester);

}

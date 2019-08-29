package cn.jsmod2.api.event.admin;

import cn.jsmod2.api.player.IPlayer;
import cn.jsmod2.core.interapi.event.IEvent;

/**
 *
 * Called when a player is banned
 * @author magiclu550
 * @see cn.jsmod2.core.interapi.event.IEvent
 * @since smod2 3.4.0 - 3.5.0
 */
public interface IBanEvent extends IEvent {

    /**
     * @return The player who is banned
     */
    IPlayer getPlayer();

    /**
     * The object can be null if the event is not raised from the player, but from a plugin or console
     * @return The admin who bans the player
     */
    IPlayer getAdmin();

    /**
     * @return The duration of the ban
     */
    int getDuration();

    /**
     * Set the duration of the ban
     * @param duration the duration of the ban
     */
    void setDuration(int duration);

    /**
     * @return The reason of the ban
     */
    String getReason();

    /**
     * Set the reason of the ban
     * @param reason the reason the ban
     */
    void setReason(String reason);

    /**
     * @return The result of the ban
     */
    String getResult();

    /**
     * Set the result of the ban
     * @param result the result of the ban
     */
    void setResult(String result);

    /**
     * @return Was the ban allowed?
     */
    boolean isAllowBan();

    /**
     * Set the ban allowed
     * @param allowBan the ban is allowed or not
     */
    void setAllowBan(boolean allowBan);

    /**
     * Set the admin who bans the player
     * @param admin the admin
     */
    void setAdmin(IPlayer admin);
}

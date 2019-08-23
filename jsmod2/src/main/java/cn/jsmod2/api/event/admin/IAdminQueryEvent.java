package cn.jsmod2.api.event.admin;

import cn.jsmod2.api.player.IPlayer;
import cn.jsmod2.core.interapi.event.IEvent;

/**
 *
 *
 * Called when an cn.jsmod2.admin queries the cn.jsmod2.server.
 * @author YorokobiMaster #(doc)
 * @author magiclu550
 *
 */
public interface IAdminQueryEvent extends IEvent {

    /**
     * @return The cn.jsmod2.player who queried the cn.jsmod2.server
     */
    IPlayer getAdmin();

    /**
     * Set the cn.jsmod2.player who queried the cn.jsmod2.server
     * @param admin cn.jsmod2.admin
     */
    void setAdmin(IPlayer admin);


    /**
     *
     * @return The query
     *
     */
    String getQuery();

    /**
     * Set the query
     * @param query The query
     */

    void setQuery(String query);


    /**
     * @return The output of the query
     */
    String getOutput();

    /**
     * Set the query
     * @param output the output of the query
     */
    void setOutput(String output);

    /**
     * @return Was the query handled?
     */
    boolean isHandled();

    /**
     * Set the handled
     * @param handled Was the query handled?
     */

    void setHandled(boolean handled);


    /**
     *
     * @return Was the query successful?
     */
    boolean isSuccessful();

    /**
     * Set the query successful;
     * @param successful set query successful
     */
    void setSuccessful(boolean successful);

}

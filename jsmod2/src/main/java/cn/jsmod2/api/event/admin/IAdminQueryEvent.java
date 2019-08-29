package cn.jsmod2.api.event.admin;

import cn.jsmod2.api.player.IPlayer;
import cn.jsmod2.core.interapi.event.IEvent;

/**
 *
 *
 * 在管理员查询服务器时，会触发该事件
 * 如管理员在游戏中查询服务器玩家等，或者需要添加的过程
 * Called when an cn.jsmod2.admin queries the cn.jsmod2.server.
 * @author magiclu550
 * @see cn.jsmod2.core.interapi.event.IEvent
 * @since smod2 3.4.0-3.5.0
 *
 */
public interface IAdminQueryEvent extends IEvent {

    /**
     * 获得此时管理员的对象
     * @return The cn.jsmod2.player who queried the cn.jsmod2.server
     */
    IPlayer getAdmin();

    /**
     * 设置此时的管理员对象
     * Set the cn.jsmod2.player who queried the cn.jsmod2.server
     * @param admin cn.jsmod2.admin
     */
    void setAdmin(IPlayer admin);


    /**
     * 获得查询内容
     * @return The query
     *
     */
    String getQuery();

    /**
     * 设置查询内容
     * Set the query
     * @param query The query
     */

    void setQuery(String query);


    /**
     * 获得查询后的输出内容
     * @return The output of the query
     */
    String getOutput();

    /**
     * 设置输出内容
     * Set the output of the query
     * @param output the output of the query
     */
    void setOutput(String output);

    /**
     * 是否处理了查询内容，如果处理了，返回true，如果没有处理
     * 返回false
     * @return Was the query handled?
     */
    boolean isHandled();

    /**
     * 设置处理是否成功，如果想使得其不成功，则设置为false
     * Set the handled
     * @param handled Was the query handled?
     */

    void setHandled(boolean handled);


    /**
     *获得查询是否成功，如果不成功，返回false
     * @return Was the query successful?
     */
    boolean isSuccessful();

    /**
     * 设置查询不成功，则设置false
     * Set the query successful;
     * @param successful set query successful
     */
    void setSuccessful(boolean successful);

}

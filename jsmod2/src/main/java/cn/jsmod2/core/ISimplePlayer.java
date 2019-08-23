/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core;

/**
 * 定义了一个玩家的基本信息
 *
 * @author magiclu550
 */

public interface ISimplePlayer extends ICommandSender {

    String getName();

    String getIpAddress();

    int getPlayerId();

    void kill();

    int getHealth();

    void addHealth(int amount);

    void personalBroadcast(int duration, String message, boolean isMonoSpaced);

}

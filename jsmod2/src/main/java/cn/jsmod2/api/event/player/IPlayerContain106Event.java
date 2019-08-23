/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.player;


import cn.jsmod2.api.player.IPlayer;

/**
 * @author kevinj
 */

public interface IPlayerContain106Event extends IPlayerEvent {

    //这个通过响应去做，获取数组，然后只获取一次
    IPlayer[] getScp106s();

    boolean isActivateContainment();

    void setActivateContainment(boolean activateContainment);

}

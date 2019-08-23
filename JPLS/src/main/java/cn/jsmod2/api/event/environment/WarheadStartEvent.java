/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.environment;

import static cn.jsmod2.network.PacketSender.sendEventGetPacket;
import static cn.jsmod2.network.PacketSender.sendEventSetPacket;

/**
 * @author Kevinj
 * @author magiclu550
 */

public class WarheadStartEvent extends WarheadEvent implements IWarheadStartEvent{
    private boolean isResumed;
    private boolean openDoorsAfter;


    public boolean isResumed() {
        isResumed = sendEventGetPacket(playerName,"Resumed",Boolean.class);
        return isResumed;
    }

    public void setResumed(boolean resumed) {
        sendEventSetPacket(playerName,"Resumed",Boolean.class);
        isResumed = resumed;
    }

    public boolean isOpenDoorsAfter() {
        openDoorsAfter = sendEventGetPacket(playerName,"OpenDoorsAfter",Boolean.class);
        return openDoorsAfter;
    }

    public void setOpenDoorsAfter(boolean openDoorsAfter) {
        sendEventSetPacket(playerName,"OpenDoorsAfter",openDoorsAfter);
        this.openDoorsAfter = openDoorsAfter;
    }

    public WarheadStartEvent(){

    }
}

/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.admin;

import cn.jsmod2.api.player.Player;
import cn.jsmod2.core.event.Event;


/**
 * @author magiclu550 #(code) jsmod2
 */

public class BanEvent extends Event {

    private Player player = new Player("");

    private Player admin = new Player("");

    private int duration;

    private String reason;

    private String result;

    private boolean allowBan;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getAdmin() {
        return admin;
    }

    public void setAdmin(Player admin) {
        this.admin = admin;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isAllowBan() {
        return allowBan;
    }

    public void setAllowBan(boolean allowBan) {
        this.allowBan = allowBan;
    }


}

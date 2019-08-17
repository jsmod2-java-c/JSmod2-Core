/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.admin;

import cn.jsmod2.api.player.IPlayer;
import cn.jsmod2.api.player.Player;
import cn.jsmod2.core.event.Event;
import cn.jsmod2.network.protocol.event.newstream.EventValueGetStream;
import cn.jsmod2.network.protocol.event.newstream.EventValueSetStream;

import static cn.jsmod2.network.PacketSender.sendGetPacket;
import static cn.jsmod2.network.PacketSender.sendSetPacket;


/**
 * @author magiclu550 #(code) jsmod2
 */

public class BanEvent extends Event implements IBanEvent{

    private IPlayer player = new Player("");

    private IPlayer admin = new Player("");

    private int duration;

    private String reason;

    private String result;

    private boolean allowBan;

    public IPlayer getPlayer() {
        return player;
    }

    public void setPlayer(IPlayer player) {
        EventValueSetStream stream = new EventValueSetStream();
        stream.playerName = playerName;
        stream.name = "Player";
        stream.value = player;
        sendSetPacket(stream);
        this.player = player;
    }

    public IPlayer getAdmin() {
        return admin;
    }

    public void setAdmin(IPlayer admin) {
        EventValueSetStream stream = new EventValueSetStream();
        stream.playerName = playerName;
        stream.name = "Admin";
        stream.value = admin;
        sendSetPacket(stream);
        this.admin = admin;
    }

    public int getDuration() {
        EventValueGetStream stream = new EventValueGetStream(Integer.class);
        stream.playerName = playerName;
        stream.name = "Duration";
        duration = sendGetPacket(stream,Integer.class);
        return duration;
    }

    public void setDuration(int duration) {
        EventValueSetStream stream = new EventValueSetStream();
        stream.name = "Duration";
        stream.value = duration;
        stream.playerName = playerName;
        sendSetPacket(stream);
        this.duration = duration;
    }

    public String getReason() {
        EventValueGetStream stream = new EventValueGetStream(String.class);
        stream.playerName = playerName;
        stream.name = "Reason";
        reason = sendGetPacket(stream,String.class);
        return reason;
    }

    public void setReason(String reason) {
        EventValueSetStream stream = new EventValueSetStream();
        stream.playerName = playerName;
        stream.name = "Reason";
        stream.value = reason;
        sendSetPacket(stream);
        this.reason = reason;
    }

    public String getResult() {
        EventValueGetStream stream = new EventValueGetStream(String.class);
        stream.playerName = playerName;
        stream.name = "Result";
        result = sendGetPacket(stream,String.class);
        return result;
    }

    public void setResult(String result) {
        EventValueSetStream stream = new EventValueSetStream();
        stream.playerName = playerName;
        stream.name = "Result";
        stream.value = result;
        sendSetPacket(stream);
        this.result = result;
    }

    public boolean isAllowBan() {
        EventValueGetStream stream = new EventValueGetStream(Boolean.class);
        stream.playerName = playerName;
        stream.name = "AlloBan";
        allowBan = sendGetPacket(stream,Boolean.class);
        return allowBan;
    }

    public void setAllowBan(boolean allowBan) {
        EventValueSetStream stream = new EventValueSetStream();
        stream.playerName = playerName;
        stream.name = "AllowBan";
        stream.value = allowBan;
        sendSetPacket(stream);
        this.allowBan = allowBan;
    }


}

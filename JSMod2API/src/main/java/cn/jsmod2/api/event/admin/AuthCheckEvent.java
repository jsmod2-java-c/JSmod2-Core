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
import cn.jsmod2.api.server.AuthType;
import cn.jsmod2.core.ApiId;
import cn.jsmod2.core.event.Event;
import cn.jsmod2.network.PacketSender;
import cn.jsmod2.network.protocol.event.newstream.EventValueGetStream;
import cn.jsmod2.network.protocol.event.newstream.EventValueSetStream;

import static cn.jsmod2.network.PacketSender.sendGetPacket;
import static cn.jsmod2.network.PacketSender.sendSetPacket;


/**
 * @author magiclu550 #(code) jsmod2
 */

public class AuthCheckEvent extends Event implements IAuthCheckEvent{

    private IPlayer requester = new Player("");

    private AuthType type;

    private boolean allow;

    private boolean successful;

    public IPlayer getRequester() {
        return requester;
    }

    public void setRequester(IPlayer requester) {
        EventValueSetStream stream = new EventValueSetStream();
        stream.name = "Requester";
        stream.value = requester;
        stream.playerName = playerName;
        sendSetPacket(stream);
        this.requester = requester;
    }

    public AuthType getType() {
        EventValueGetStream stream = new EventValueGetStream(AuthType.class);
        stream.name = "AuthType";
        stream.playerName = playerName;
        type = sendGetPacket(stream,AuthType.class);
        return type;
    }

    public void setType(AuthType type) {
        EventValueSetStream stream = new EventValueSetStream();
        stream.playerName = playerName;
        stream.name = "Type";
        stream.value = type;
        sendSetPacket(stream);
        this.type = type;
    }

    public boolean isAllow() {
        EventValueGetStream stream = new EventValueGetStream(Boolean.class);
        stream.playerName = playerName;
        stream.name = "Allow";
        allow = sendGetPacket(stream,Boolean.class);
        return allow;
    }

    public void setAllow(boolean allow) {
        EventValueSetStream stream = new EventValueSetStream();
        stream.playerName = playerName;
        stream.name = "Allow";
        stream.value = allow;
        sendSetPacket(stream);
        this.allow = allow;
    }

    public boolean isSuccessful() {
        EventValueGetStream stream = new EventValueGetStream(Boolean.class);
        stream.playerName = playerName;
        stream.name = "Successful";
        successful = sendGetPacket(stream,Boolean.class);
        return successful;
    }

    public void setSuccessful(boolean successful) {
        EventValueSetStream stream = new EventValueSetStream();
        stream.playerName = playerName;
        stream.name = "Successful";
        stream.value = successful;
        sendSetPacket(stream);
        this.successful = successful;
    }


}

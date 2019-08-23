/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
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
import cn.jsmod2.network.PacketSender;
import cn.jsmod2.network.protocol.event.admin.AdminQueryAdminSetPacket;
import cn.jsmod2.network.protocol.event.admin.AdminQueryQueryGetPacket;
import cn.jsmod2.network.protocol.event.admin.AdminQueryQuerySetPacket;
import cn.jsmod2.network.protocol.event.newstream.EventValueGetStream;
import cn.jsmod2.network.protocol.event.newstream.EventValueSetStream;
import cn.jsmod2.network.protocol.event.newstream.GetTypes;

/**
 * @author magiclu550 #(code) jsmod2
 */

public class AdminQueryEvent extends Event implements IAdminQueryEvent{

    private IPlayer admin = new Player("");

    private String query;

    private String output;

    private boolean handled;

    private boolean successful;

    public IPlayer getAdmin() {
        return admin;
    }

    public void setAdmin(IPlayer admin) {
        AdminQueryAdminSetPacket set = new AdminQueryAdminSetPacket();
        set.playerName = playerName;
        set.admin = (Player) admin;
        set.send();
        this.admin = admin;
    }

    public String getQuery() {
        AdminQueryQueryGetPacket packet = new AdminQueryQueryGetPacket();
        packet.playerName = playerName;
        query = packet.send();
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
        AdminQueryQuerySetPacket packet = new AdminQueryQuerySetPacket();
        packet.playerName = playerName;
        packet.query = query;
        packet.send();
    }

    public String getOutput() {
        EventValueGetStream stream = new EventValueGetStream(String.class);
        stream.playerName = playerName;
        stream.getType = GetTypes.GET;
        stream.name = "Output";
        output = PacketSender.sendGetPacket(stream,String.class);
        return output;
    }

    public void setOutput(String output) {
        EventValueSetStream stream = new EventValueSetStream();
        stream.playerName = playerName;
        stream.name = "Output";
        stream.value = output;
        PacketSender.sendSetPacket(stream);
        this.output = output;
    }

    public boolean isHandled() {
        EventValueGetStream stream = new EventValueGetStream(Boolean.class);
        stream.playerName = playerName;
        stream.name = "Handled";
        handled = PacketSender.sendGetPacket(stream,Boolean.class);
        return handled;
    }

    public void setHandled(boolean handled) {
        EventValueSetStream stream = new EventValueSetStream();
        stream.playerName = playerName;
        stream.name = "Handled";
        stream.value = handled;
        PacketSender.sendSetPacket(stream);
        this.handled = handled;
    }

    public boolean isSuccessful() {
        EventValueGetStream stream = new EventValueGetStream(Boolean.class);
        stream.playerName = playerName;
        stream.name = "Successful";
        successful = PacketSender.sendGetPacket(stream,Boolean.class);
        return successful;
    }

    public void setSuccessful(boolean successful) {
        EventValueSetStream stream = new EventValueSetStream();
        stream.playerName = playerName;
        stream.name = "Successful";
        stream.value = successful;
        PacketSender.sendSetPacket(stream);
        this.successful = successful;
    }

}

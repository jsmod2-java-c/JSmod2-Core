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
import cn.jsmod2.network.protocol.event.admin.AdminQueryAdminSetPacket;
import cn.jsmod2.network.protocol.event.admin.AdminQueryQueryGetPacket;
import cn.jsmod2.network.protocol.event.admin.AdminQueryQuerySetPacket;

/**
 * @author magiclu550 #(code) jsmod2
 */

public class AdminQueryEvent extends Event implements IAdminQueryEvent{

    private Player admin = new Player("");

    private String query;

    private String output;

    private boolean handled;

    private boolean successful;

    public Player getAdmin() {
        return admin;
    }

    public void setAdmin(Player admin) {
        AdminQueryAdminSetPacket set = new AdminQueryAdminSetPacket();
        set.playerName = playerName;
        set.admin = admin;
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
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public boolean isHandled() {
        return handled;
    }

    public void setHandled(boolean handled) {
        this.handled = handled;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

}

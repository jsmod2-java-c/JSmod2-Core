/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */

package cn.jsmod2.api.server;


import cn.jsmod2.api.map.Map;
import cn.jsmod2.api.player.Player;
import cn.jsmod2.api.team.ITeamRole;
import cn.jsmod2.api.team.TeamRole;
import cn.jsmod2.core.CommandSender;
import cn.jsmod2.core.GameServer;
import cn.jsmod2.core.Server;
import cn.jsmod2.network.protocol.event.newstream.GetTypes;
import cn.jsmod2.network.protocol.server.*;

import java.util.List;

/**
 * 该类换成用GetPacket获取
 */
public class Smod2Server extends CommandSender implements GameServer {

    @Deprecated
    private String name;
    @Deprecated
    private int port;
    @Deprecated
    private String ipAddress;

    private Round round;

    private Map map;
    @Deprecated
    private int numPlayers;
    @Deprecated
    private int maxPlayers;

    private String playerListTitle;

    public Smod2Server() {
        super("CONSOLE","all","console","cn.jsmod2.admin","cn.jsmod2.player","nobody");
    }

    public Server getRuntimeServer(){
        return Server.getRuntime().running();
    }

    @Deprecated
    public Smod2Server updateServer(GameServer server){
        if(server instanceof Smod2Server) {
            Smod2Server smod2Server = (Smod2Server)server;
            Server.getRuntime().running().getLock().lock();
            this.ipAddress = smod2Server.ipAddress;
            this.map = smod2Server.map;
            this.maxPlayers = smod2Server.maxPlayers;
            this.numPlayers = smod2Server.numPlayers;
            this.name = smod2Server.name;
            this.round = smod2Server.round;
            this.port = smod2Server.port;
            Server.getRuntime().running().getLock().unlock();
            return this;
        }
        return null;
    }

    public String getPlayerListTitle() {
        SimpleServerFieldStream stream = new SimpleServerFieldStream(String.class);
        stream.field = "PlayerListTitle";
        stream.isWrite = false;
        playerListTitle = (String) stream.send();
        return playerListTitle;
    }

    public void setPlayerListTitle(String playerListTitle) {
        SimpleServerFieldStream stream = new SimpleServerFieldStream(Void.class);
        stream.field = "PlayerListTitle";
        stream.isWrite = true;
        stream.value = playerListTitle;
        stream.send();
        this.playerListTitle = playerListTitle;
    }

    public int getPort() {
        GetPortPacket packet = new GetPortPacket();
        return packet.send();
    }


    public String getIpAddress() {
        GetIpAddressPacket packet = new GetIpAddressPacket();
        return packet.send();
    }


    public Round getRound() {
        if(round == null){
            round = new Round();
        }
        return round;
    }

    public Map getMap() {
        if(map == null){
            map = new Map();
        }
        return map;
    }


    public int getNumPlayers() {
        GetNumPlayersPacket playersPacket = new GetNumPlayersPacket();
        return playersPacket.send();
    }


    public int getMaxPlayers() {
        GetMaxPlayersPacket playersPacket = new GetMaxPlayersPacket();
        return playersPacket.send();
    }

    public void setMaxPlayers(int maxPlayers) {
        SetMaxPlayersPacket playersPacket = new SetMaxPlayersPacket();
        playersPacket.maxPlayers = maxPlayers;
        playersPacket.send();
    }

    public List<Player> getPlayers() {
        return getPlayers("");
    }

    public List<Player> getPlayers(String filter){
        GetPlayerPacket playerPacket = new GetPlayerPacket();
        playerPacket.filter = filter;
        return playerPacket.send();
    }

    @Override
    public void personalBroadcast(int i, String s, boolean b) {

    }

    @Override
    public List<IConnection> getConnections(String s) {
        SimpleServerMethodPacket packet = new SimpleServerMethodPacket(Connection.class);
        packet.method = "GetConnections";
        packet.args = new String[]{s};
        packet.getTypes = GetTypes.GET_PROTOCOL_ARRAY_WITHOUT_LIST_IN;
        return (List<IConnection>) packet.send();
    }

    @Override
    public List<ITeamRole> getRoles(String s) {
        SimpleServerMethodPacket packet = new SimpleServerMethodPacket(TeamRole.class);
        packet.method = "GetRoles";
        packet.args = new String[]{s};
        packet.getTypes = GetTypes.GET_PROTOCOL_ARRAY_WITHOUT_LIST_IN;
        return (List<ITeamRole>)packet.send();
    }

    @Override
    public String getAppFolder(boolean b, boolean b1, boolean b2, boolean b3) {
        SimpleServerMethodPacket packet = new SimpleServerMethodPacket(String.class);
        packet.method = "GetAppFolder";
        packet.args = new String[]{b+"",b1+"",b2+"",b3+""};
        return (String)packet.send();
    }

    @Override
    public boolean banSteamId(String s, String s1, int i, String s2, String s3) {
        SimpleServerMethodPacket packet = new SimpleServerMethodPacket(Boolean.class);
        packet.method = "BanSteamId";
        packet.args = new String[]{s,s1,i+"",s2,s3};
        return (Boolean) packet.send();
    }

    @Override
    public boolean banIpAddress(String s, String s1, int i, String s2, String s3) {
        SimpleServerMethodPacket packet = new SimpleServerMethodPacket(Boolean.class);
        packet.method = "BanIpAddress";
        packet.args = new String[]{s,s1,i+"",s2,s3};
        return (Boolean)packet.send();
    }
}

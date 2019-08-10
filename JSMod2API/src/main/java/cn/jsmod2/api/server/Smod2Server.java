/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */

package cn.jsmod2.api.server;


import cn.jsmod2.api.map.Map;
import cn.jsmod2.api.player.Player;
import cn.jsmod2.core.CommandSender;
import cn.jsmod2.core.GameServer;
import cn.jsmod2.core.Server;
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

    private cn.jsmod2.api.map.Map map;
    @Deprecated
    private int numPlayers;
    @Deprecated
    private int maxPlayers;

    public Smod2Server() {
        super("CONSOLE","all","console","admin","player","nobody");
    }

    public Server getRuntimeServer(){
        return Server.getSender().getServer();
    }

    @Deprecated
    public Smod2Server updateServer(GameServer server){
        if(server instanceof Smod2Server) {
            Smod2Server smod2Server = (Smod2Server)server;
            Server.getSender().getServer().getLock().lock();
            this.ipAddress = smod2Server.ipAddress;
            this.map = smod2Server.map;
            this.maxPlayers = smod2Server.maxPlayers;
            this.numPlayers = smod2Server.numPlayers;
            this.name = smod2Server.name;
            this.round = smod2Server.round;
            this.port = smod2Server.port;
            Server.getSender().getServer().getLock().unlock();
            return this;
        }
        return null;
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
        GetPlayerPacket playerPacket = new GetPlayerPacket();
        return playerPacket.send();
    }

    @Override
    public void personalBroadcast(int i, String s, boolean b) {

    }
}

/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */

package cn.jsmod2.api.server;

//TODO Smod2Server 计划5.15完成


import cn.jsmod2.CommandSender;
import cn.jsmod2.Server;
import cn.jsmod2.annotations.FieldInsert;
import cn.jsmod2.annotations.UseForServerInit;
import cn.jsmod2.api.map.Map;
import cn.jsmod2.api.player.Player;

import java.util.List;

public class Smod2Server extends CommandSender {

    private String name;
    private int port;
    private String ipAddress;
    private Round round;
    private cn.jsmod2.api.map.Map map;
    private int numPlayers;
    private int maxPlayers;

    @FieldInsert
    private List<Player> players;

    public Smod2Server() {
        super("CONSOLE","all","console","admin","player","nobody");
    }

    public Server getRuntimeServer(){
        return Server.getSender().getServer();
    }


    public Smod2Server updateServer(Smod2Server server){
        Server.getSender().getServer().getLock().lock();
        this.ipAddress = server.ipAddress;
        this.map = server.map;
        this.maxPlayers = server.maxPlayers;
        this.numPlayers = server.numPlayers;
        this.name = server.name;
        this.round = server.round;
        this.port = server.port;
        Server.getSender().getServer().getLock().unlock();
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }
    @UseForServerInit
    public void setPort(int port) {
        this.port = port;
    }

    public String getIpAddress() {
        return ipAddress;
    }
    @UseForServerInit
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Round getRound() {
        return round;
    }
    @UseForServerInit
    public void setRound(Round round) {
        this.round = round;
    }

    public Map getMap() {
        return map;
    }
    @UseForServerInit
    public void setMap(Map map) {
        this.map = map;
    }

    public int getNumPlayers() {
        return numPlayers;
    }
    @UseForServerInit
    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public List<String> getPowers() {
        return super.getPowers();
    }

}

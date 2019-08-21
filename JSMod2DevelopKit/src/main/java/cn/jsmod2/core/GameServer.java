package cn.jsmod2.core;


import cn.jsmod2.api.map.IMap;
import cn.jsmod2.api.player.IPlayer;
import cn.jsmod2.api.server.IConnection;
import cn.jsmod2.api.server.IRound;
import cn.jsmod2.api.team.ITeamRole;
import cn.jsmod2.api.team.Role;
import cn.jsmod2.core.annotations.PlayerName;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用于封装其他语言服务器信息，如果要将该服务器做成与其他服务器交互的话
 *
 * @author magiclu550
 */

public interface GameServer {

    List<? extends IPlayer> getPlayers();

    @Deprecated
    GameServer updateServer(GameServer server);

    //3.5.0
    String getPlayerListTitle();

    //3.5.0
    void setPlayerListTitle(String playerListTitle);

    default int getPort() {
        return 0;
    }


    default String getIpAddress() {
        return "";
    }


    default IRound getRound() {
        return null;
    }

    default IMap getMap() {
        return null;
    }


    default int getNumPlayers() {
        return 0;
    }


    default int getMaxPlayers() {
        return 0;
    }

    default void setMaxPlayers(int maxPlayers) {
    }


    default List<? extends IPlayer> getPlayers(String filter){
        return getPlayers().stream().filter(name -> ((IPlayer) name).getName().equals(filter)).collect(Collectors.toList());
    }

    default List<? extends IPlayer> getPlayers(Role role){
        return getPlayers().stream().filter(name -> ((IPlayer) name).getTeamRole().getRole().equals(role)).collect(Collectors.toList());
    }

    default List<? extends IPlayer> getPlayers(Role[] role){
        return getPlayers().stream().filter(name -> Arrays.asList(role).contains(((IPlayer) name).getTeamRole().getRole())).collect(Collectors.toList());
    }

    default IPlayer getPlayer(int id){
        List<IPlayer> players = (List<IPlayer>) getPlayers();
        for(IPlayer player:players){
            if(player.getPlayerId() == id){
                return player;
            }
        }
        return null;
    }

    List<IConnection> getConnections(String filter);

    default List<IConnection> getConnections(){
        return getConnections("");
    }

    List<ITeamRole> getRoles(String filter);

    default List<ITeamRole> getRoles(){
        return getRoles("");
    }

    String getAppFolder(boolean shared,boolean addSeparator,boolean addPort,boolean addConfigs);

    default String getAppFolder(){
        return getAppFolder(false,false,false,false);
    }

    boolean banSteamId(String username,String steamId,int duration,String reason,String issuer);

    default boolean banSteamId(String username,String steamId,int duration,String reason){
        return banSteamId(username,steamId,duration,reason,"Server");
    }

    boolean banIpAddress(String username,String ipAddress,int duration,String reason,String issuer);

    default boolean banIpAddress(String username,String ipAddress,int duration,String reason){
        return banIpAddress(username, ipAddress, duration, reason,"Server");
    }
}

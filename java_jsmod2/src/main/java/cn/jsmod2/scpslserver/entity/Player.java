/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.scpslserver.entity;

import cn.jsmod2.scpslserver.CommandSender;
import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.utils.player.DamageType;
import cn.jsmod2.scpslserver.utils.player.RadioStatus;
import cn.jsmod2.scpslserver.utils.player.Scp079Data;

public class Player extends CommandSender implements IPlayer {

    private TeamRole teamRole;

    private String ipAddress;

    private int playerId;

    private String streamId;

    private RadioStatus radioStatus;

    private boolean overwatchMode;

    private boolean doNotTrack;

    private Scp079Data scp079Data;

    private int health;

    public Player(String name){
        super(name,"all","player");
    }


    public TeamRole getTeamRole() {
        return teamRole;
    }

    public void setTeamRole(TeamRole teamRole) {
        this.teamRole = teamRole;
    }


    public String getIpAddress() {
        return ipAddress;
    }

    /** java bean */
    @UseForServerInit
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPlayerId() {
        return playerId;
    }

    /** java bean */
    @UseForServerInit
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getStreamId() {
        return streamId;
    }

    /** java bean */
    @UseForServerInit
    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    public RadioStatus getRadioStatus() {
        return radioStatus;
    }

    public void setRadioStatus(RadioStatus radioStatus) {
        this.radioStatus = radioStatus;
    }

    public boolean isOverwatchMode() {
        return overwatchMode;
    }

    /** java bean */
    @UseForServerInit
    public void setOverwatchMode(boolean overwatchMode) {
        this.overwatchMode = overwatchMode;
    }

    public boolean isDoNotTrack() {
        return doNotTrack;
    }

    /** java bean */
    @UseForServerInit
    public void setDoNotTrack(boolean doNotTrack) {
        this.doNotTrack = doNotTrack;
    }

    public Scp079Data getScp079Data() {
        return scp079Data;
    }

    /** java bean */
    @UseForServerInit
    public void setScp079Data(Scp079Data scp079Data) {
        this.scp079Data = scp079Data;
    }

    public void kill(DamageType type){

    }

    public void kill(){
        //default NUKE
        //发json数据包。远程调用方法
        //C#数据包拥有参数数据字段
    }

    public int getHealth(){
        return health;
    }

    public void addHealth(int amount){
        health += amount;
        //发包
    }

}

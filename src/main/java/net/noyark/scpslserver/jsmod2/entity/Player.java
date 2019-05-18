package net.noyark.scpslserver.jsmod2.entity;

import net.noyark.scpslserver.jsmod2.CommandSender;
import net.noyark.scpslserver.jsmod2.utils.player.DamageType;
import net.noyark.scpslserver.jsmod2.utils.player.RadioStatus;
import net.noyark.scpslserver.jsmod2.utils.player.Scp079Data;

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
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPlayerId() {
        return playerId;
    }

    /** java bean */
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getStreamId() {
        return streamId;
    }

    /** java bean */
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
    public void setOverwatchMode(boolean overwatchMode) {
        this.overwatchMode = overwatchMode;
    }

    public boolean isDoNotTrack() {
        return doNotTrack;
    }

    /** java bean */
    public void setDoNotTrack(boolean doNotTrack) {
        this.doNotTrack = doNotTrack;
    }

    public Scp079Data getScp079Data() {
        return scp079Data;
    }

    /** java bean */
    public void setScp079Data(Scp079Data scp079Data) {
        this.scp079Data = scp079Data;
    }

    public void kill(DamageType type){

    }

    public void kill(){
        //default NUKE
    }

    public int getHealth(){
        return 0;
    }
    /** java-bean 用于调接赋值 */
    public void setHealth(int health){

    }

    public void addHealth(){

    }



}

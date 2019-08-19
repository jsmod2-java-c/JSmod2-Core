/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.player;


import cn.jsmod2.api.item.Item;
import cn.jsmod2.api.item.ItemType;
import cn.jsmod2.api.team.ITeamRole;
import cn.jsmod2.api.team.Role;
import cn.jsmod2.api.team.TeamRole;
import cn.jsmod2.api.user.UserGroup;
import cn.jsmod2.core.CommandSender;
import cn.jsmod2.core.Server;
import cn.jsmod2.core.math.Vector;
import cn.jsmod2.network.DoGetStream;
import cn.jsmod2.network.DoStream;
import cn.jsmod2.network.SimpleGetStream;
import cn.jsmod2.network.SimpleSetStream;

import java.io.Serializable;
import java.util.List;

public class Player extends CommandSender implements IPlayer, Serializable,Cloneable {

    private ITeamRole teamRole = new TeamRole();

    private String ipAddress;

    private int playerId;

    private String steamId;

    private RadioStatus radioStatus;

    private boolean overwatchMode;

    private boolean doNotTrack;

    private Scp079Data scp079Data = new Scp079Data();

    public Player(String name){
        super(name,"all","player");
    }


    public ITeamRole getTeamRole() {
        return teamRole;
    }

    public void setTeamRole(ITeamRole teamRole) {
        SimpleSetStream stream = new SimpleSetStream();
        stream.write(playerName,"TeamRole",teamRole);
        this.teamRole = teamRole;
    }


    public String getIpAddress() {
        SimpleGetStream stream = new SimpleGetStream(String.class);
        ipAddress = stream.read(playerName,"IpAddress",String.class);
        return ipAddress;
    }


    public int getPlayerId() {
        SimpleGetStream stream = new SimpleGetStream(Integer.class);
        playerId = stream.read(playerName,"PlayerId",Integer.class);
        return playerId;
    }


    public String getSteamId() {
        SimpleGetStream stream = new SimpleGetStream(String.class);
        steamId = stream.read(playerName,"SteamId",String.class);
        return steamId;
    }


    public RadioStatus getRadioStatus() {
        SimpleGetStream stream = new SimpleGetStream(RadioStatus.class);
        radioStatus = stream.read(playerName,"RadioStatus",RadioStatus.class);
        return radioStatus;
    }

    public void setRadioStatus(RadioStatus radioStatus) {
        SimpleSetStream stream = new SimpleSetStream();
        stream.write(playerName,"RadioStatus",radioStatus);
        this.radioStatus = radioStatus;
    }

    public boolean isOverwatchMode() {
        SimpleGetStream stream = new SimpleGetStream(Boolean.class);
        overwatchMode = stream.read(playerName,"OverWatchMode",Boolean.class);
        return overwatchMode;
    }


    public boolean isDoNotTrack() {
        SimpleGetStream stream = new SimpleGetStream(Boolean.class);
        doNotTrack = stream.read(playerName,"DoNotTrack",Boolean.class);
        return doNotTrack;
    }


    public Scp079Data getScp079Data() {
        return scp079Data;
    }


    public void kill(DamageType type){
        DoStream stream = new DoStream();
        stream.method = "Kill";
        stream.args = new String[]{"'"+type+"'"};
        stream.playerName = playerName;
        stream.send();
    }

    public void kill(){
        kill(DamageType.NUKE);
    }

    public int getHealth(){
        DoGetStream stream = new DoGetStream(Integer.class);
        stream.method = "GetHealth";
        return (Integer)stream.send();
    }

    public void addHealth(int amount){
        DoStream stream = new DoStream();
        stream.args = new String[]{amount+""};
        stream.method = "AddHealth";
        stream.playerName = playerName;
        stream.send();
        //发包
    }

    public void damage(int amount,DamageType type){
        DoStream stream = new DoStream();
        stream.playerName = playerName;
        stream.method = "Damage";
        stream.args = new String[]{amount+"","'"+type+"'"};
        stream.send();
    }

    public void damage(int amount){
        damage(amount,DamageType.NUKE);
    }

    public void setHealth(int amount,DamageType type){
        DoStream stream = new DoStream();
        stream.method = "SetHealth";
        stream.playerName = playerName;
        stream.args = new String[]{amount+"","'"+type+"'"};
    }

    public void setHealth(int amount){
        setHealth(amount,DamageType.NUKE);
    }

    public int getAmmo(AmmoType type){
        DoGetStream stream = new DoGetStream(AmmoType.class);
        stream.method = "GetAmmo";
        stream.args = new String[]{"'"+type+"'"};
        stream.playerName = playerName;
        return (Integer) stream.send();
    }

    public void setAmmo(AmmoType type,int amount){
        DoStream stream = new DoStream();
        stream.playerName = playerName;
        stream.method = "SetAmmo";
        stream.args = new String[]{"'"+type+"'",amount+""};
        stream.send();
    }

    public Vector getPosition(){
        DoGetStream stream = new DoGetStream(Vector.class);
        stream.playerName = playerName;
        stream.method = "GetPosition";
        return (Vector) stream.send();
    }

    public void teleport(Vector pos,boolean unstuck){
        DoGetStream stream = new DoGetStream(Vector.class);
        stream.method = "Teleport";
        stream.playerName = playerName;
        stream.args = new String[]{pos.toString(),unstuck+""};
        stream.send();
    }

    public void teleport(Vector pos){
        teleport(pos,false);
    }

    public void setRank(String color,String text,String group){
        DoStream stream = new DoStream();
        stream.method = "SetRank";
        stream.playerName = playerName;
        stream.args = new String[]{color,text,group};
        stream.send();
    }

    public String getRankName(){
        DoGetStream stream = new DoGetStream(String.class);
        stream.method = "GetRankName";
        stream.playerName = playerName;
        return (String) stream.send();
    }

    public void disConnect(){
        DoStream stream = new DoStream();
        stream.method = "DisConnect";
        stream.playerName = playerName;
        stream.send();
    }

    public void disConnect(String message){
        DoStream stream = new DoStream();
        stream.playerName = playerName;
        stream.method = "DisConnect";
        stream.args =new String[]{message};
        stream.send();
    }
    public void ban(int duration){
        DoStream stream = new DoStream();
        stream.playerName = playerName;
        stream.method = "Ban";
        stream.args = new String[]{duration+""};
        stream.send();
    }

    public void ban(int duration,String message){
        DoStream stream = new DoStream();
        stream.method = "Ban";
        stream.args = new String[]{duration+"",message};
        stream.playerName = playerName;
        stream.send();
    }

    public Item giveItem(ItemType type){
        return null;
    }

    public List<Item> getInventory(){
        return null;
    }

    public Item getCurrentItem(){
        return null;
    }

    public void setCurrentItem(ItemType type){

    }

    public int getCurrentItemIndex(){
        return 0;
    }

    public void setCurrentItemIndex(int index){

    }

    public boolean hasItem(ItemType type){
        return false;
    }

    public int getItemIndex(ItemType type){
        return 0;
    }

    public boolean isHandcuffed(){
        return false;
    }

    public void changeRole(Role role,boolean full,boolean spawnTeleport,boolean spawnProtected){

    }

    //这里未来解决
    public Object getGameObject(){
        return null;
    }

    public UserGroup getUserGroup(){
        return null;
    }

    public boolean runCommand(String command,String[] args){
       return Server.getSender().getServer().getPluginManager().executeCommand(command,args,this);
    }

    public boolean getGodmode(){
        return true;
    }

    public void setGodmode(boolean godmode){

    }

    public Vector getRotation(){
        return null;
    }

    public void sendConsoleMessage(String message, String color){

    }
    public void sendConsoleMessage(String message){
        sendConsoleMessage(message,"green");
    }

    public void infect(float time){

    }
    public void throwGrenade(GrenadeType grenadeType, boolean isCustomDirection, Vector direction, boolean isEnvironmentallyTriggered, Vector position, boolean isCustomForce, float throwForce, boolean slowThrow){

    }
    public void throwGrenade(GrenadeType grenadeType, boolean isCustomDirection, Vector direction, boolean isEnvironmentallyTriggered, Vector position, boolean isCustomForce, float throwForce){
        throwGrenade(grenadeType,isCustomDirection,direction,isEnvironmentallyTriggered,position,isCustomForce,throwForce,false);
    }
    public boolean getBypassMode(){
        return false;
    }
    public String getAuthToken(){
        return "";
    }
    public void hideTag(boolean enable){

    }

    public void personalBroadcast(int duration, String message, boolean isMonoSpaced){

    }

    public void personalClearBroadcasts(){

    }

    //hasPermission未来搞

    public boolean hasPermission(String permissionName){
        return false;
    }

    public Vector get106Portal(){
        return null;
    }

    public void setRadioBattery(int battery){

    }
    public void handcuffPlayer(IPlayer playerToHandcuff){

    }
    public void removeHandcuffs(){

    }
    public boolean getGhostMode(){
        return false;
    }
    public void setGhostMode(boolean ghostMode, boolean visibleToSpec, boolean visibleWhenTalking){

    }

}

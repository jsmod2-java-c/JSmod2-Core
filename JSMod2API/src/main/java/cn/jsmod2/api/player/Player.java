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

import java.io.Serializable;
import java.util.List;

public class Player extends CommandSender implements IPlayer, Serializable,Cloneable {

    private ITeamRole teamRole = new TeamRole();

    private String ipAddress;

    private int playerId;

    private String streamId;

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
        this.teamRole = teamRole;
    }


    public String getIpAddress() {
        return ipAddress;
    }


    public int getPlayerId() {
        return playerId;
    }


    public String getStreamId() {
        return streamId;
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


    public boolean isDoNotTrack() {
        return doNotTrack;
    }


    public Scp079Data getScp079Data() {
        return scp079Data;
    }


    public void kill(DamageType type){

    }

    public void kill(){
        kill(DamageType.NUKE);
    }

    public int getHealth(){
        return 0;
    }

    public void addHealth(int amount){
        //发包
    }

    public void damage(int amount,DamageType type){

    }

    public void damage(int amount){
        damage(amount,DamageType.NUKE);
    }

    public void setHealth(int amount,DamageType type){

    }

    public void setHealth(int amount){
        setHealth(amount,DamageType.NUKE);
    }

    public int getAmmo(AmmoType type){
        return 0;
    }

    public void setAmmo(AmmoType type,int amount){

    }

    public Vector getPosition(){
        return null;
    }

    public void teleport(Vector pos,boolean unstuck){

    }

    public void teleport(Vector pos){
        teleport(pos,false);
    }

    public void setRank(String color,String text,String group){

    }

    public String getRankName(){
        return "";
    }

    public void disConnect(){

    }

    public void disConnect(String message){

    }
    public void ban(int duration){

    }

    public void ban(int duration,String message){

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

    @Override
    public String toString() {
        return "Player{" +
                "teamRole=" + teamRole +
                ", ipAddress='" + ipAddress + '\'' +
                ", playerId=" + playerId +
                ", streamId='" + streamId + '\'' +
                ", radioStatus=" + radioStatus +
                ", overwatchMode=" + overwatchMode +
                ", doNotTrack=" + doNotTrack +
                ", scp079Data=" + scp079Data +
                '}';
    }
}

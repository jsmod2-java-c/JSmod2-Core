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
import cn.jsmod2.network.*;
import cn.jsmod2.network.protocol.item.GetItemPacket;
import cn.jsmod2.network.protocol.player.GetCurrentItemPacket;
import cn.jsmod2.network.protocol.player.GetInventoryPacket;
import cn.jsmod2.network.protocol.player.GetUserGroupPacket;
import cn.jsmod2.network.protocol.player.GiveItemPacket;

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
        GiveItemPacket packet = new GiveItemPacket();
        packet.playerName = playerName;
        packet.type = type;
        return (Item)packet.send();
    }

    @SuppressWarnings("unchecked")
    public List<Item> getInventory(){
        GetInventoryPacket packet = new GetInventoryPacket();
        packet.playerName = playerName;
        return packet.send();
    }

    public Item getCurrentItem(){
        GetCurrentItemPacket packet = new GetCurrentItemPacket();
        packet.playerName = playerName;
        return packet.send();
    }

    public void setCurrentItem(ItemType type){
        DoStream stream = new DoStream();
        stream.playerName = playerName;
        stream.method = "SetCurrentItem";
        stream.args = new String[]{"'"+type+"'"};
        stream.send();
    }

    public int getCurrentItemIndex(){
        DoGetStream stream = new DoGetStream(Integer.class);
        stream.playerName = playerName;
        stream.method = "GetCurrentItem";
        return (Integer) stream.send();
    }

    public void setCurrentItemIndex(int index){
        DoStream stream = new DoStream();
        stream.playerName = playerName;
        stream.method = "SetCurrentItemIndex";
        stream.args = new String[]{index+""};
        stream.send();
    }

    public boolean hasItem(ItemType type){
        DoGetStream stream = new DoGetStream(ItemType.class);
        stream.method = "HasItem";
        stream.args = new String[]{"'"+type+"'"};
        stream.playerName = playerName;
        return (Boolean)stream.send();
    }

    public int getItemIndex(ItemType type){
        DoGetStream stream = new DoGetStream(ItemType.class);
        stream.method = "GetItemIndex";
        stream.playerName = playerName;
        stream.args = new String[]{"'"+type+"'"};
        return (Integer) stream.send();
    }

    public boolean isHandcuffed(){
        DoGetStream stream = new DoGetStream(Boolean.class);
        stream.method = "IsHandcuffed";
        stream.playerName = playerName;
        return (Boolean)stream.send();
    }

    public void changeRole(Role role,boolean full,boolean spawnTeleport,boolean spawnProtected){
        DoStream stream = new DoStream();
        stream.playerName = playerName;
        stream.method = "ChangeRole";
        stream.args = new String[]{"'"+role+"'",full+"",spawnTeleport+"",spawnProtected+""};
        stream.send();
    }

    //这里未来解决
    public Object getGameObject(){
        return null;
    }

    public UserGroup getUserGroup(){
        GetUserGroupPacket packet = new GetUserGroupPacket();
        packet.playerName = playerName;
        return packet.send();
    }

    public boolean runCommand(String command,String[] args){
       return Server.getSender().getServer().getPluginManager().executeCommand(command,args,this);
    }

    public boolean getGodmode(){
        DoGetStream stream = new DoGetStream(Boolean.class);
        stream.playerName = playerName;
        stream.method = "GetGodmode";
        return (Boolean) stream.send();
    }

    public void setGodmode(boolean godmode){
        DoStream stream = new DoStream();
        stream.playerName = playerName;
        stream.method = "SetGodMode";
        stream.args = new String[]{godmode+""};
        stream.send();
    }

    public Vector getRotation(){
        DoGetStream stream = new DoGetStream(Vector.class);
        stream.playerName = playerName;
        stream.method = "GetRotation";
        return (Vector) stream.send();
    }

    public void sendConsoleMessage(String message, String color){
        DoStream stream = new DoStream();
        stream.playerName = playerName;
        stream.method = "SendConsoleMessage";
        stream.args = new String[]{message,color};
        stream.send();
    }
    public void sendConsoleMessage(String message){
        sendConsoleMessage(message,"green");
    }

    public void infect(float time){
        DoStream stream = new DoStream();
        stream.playerName = playerName;
        stream.method = "Infect";
        stream.args = new String[]{time+""};
        stream.send();
    }
    public void throwGrenade(GrenadeType grenadeType, boolean isCustomDirection, Vector direction, boolean isEnvironmentallyTriggered, Vector position, boolean isCustomForce, float throwForce, boolean slowThrow){
        DoStream stream = new DoStream();
        stream.playerName = playerName;
        stream.method = "ThrowGrenade";
        stream.args = new String[]{"'"+grenadeType+"'",isCustomDirection+"",direction.toString(),isEnvironmentallyTriggered+"",position.toString(),isCustomForce+"",throwForce+"",slowThrow+""};
        stream.send();
    }
    public void throwGrenade(GrenadeType grenadeType, boolean isCustomDirection, Vector direction, boolean isEnvironmentallyTriggered, Vector position, boolean isCustomForce, float throwForce){
        throwGrenade(grenadeType,isCustomDirection,direction,isEnvironmentallyTriggered,position,isCustomForce,throwForce,false);
    }
    public boolean getBypassMode(){
        DoGetStream stream = new DoGetStream(Boolean.class);
        stream.playerName = playerName;
        stream.method = "GetBypassMode";
        return (Boolean) stream.send();
    }
    public String getAuthToken(){
        DoGetStream stream = new DoGetStream(String.class);
        stream.method = "GetAuthToken";
        stream.playerName = playerName;
        return (String) stream.send();
    }
    public void hideTag(boolean enable){
        DoStream doStream = new DoStream();
        doStream.method = "HideTag";
        doStream.args = new String[]{enable+""};
        doStream.playerName = playerName;
        doStream.send();
    }

    public void personalBroadcast(int duration, String message, boolean isMonoSpaced){
        DoStream doStream = new DoStream();
        doStream.method = "PersonalBroadcast";
        doStream.args = new String[]{duration+"",message,isMonoSpaced+""};
        doStream.playerName = playerName;
        doStream.send();
    }

    public void personalClearBroadcasts(){
        DoStream doStream = new DoStream();
        doStream.method = "PersonalClearBroadcasts";
        doStream.playerName = playerName;
        doStream.send();
    }

    //hasPermission未来搞

    public boolean hasPermission(String permissionName){
        DoGetStream stream = new DoGetStream(Boolean.class);
        stream.method = "HasPermission";
        stream.args = new String[]{permissionName};
        stream.playerName = playerName;
        return (Boolean)stream.send();
    }

    public Vector get106Portal(){
        DoGetStream stream = new DoGetStream(Vector.class);
        stream.method = "Get106Portal";
        stream.playerName = playerName;
        return (Vector)stream.send();
    }

    public void setRadioBattery(int battery){
        DoStream stream = new DoStream();
        stream.method = "SetRadioBattery";
        stream.playerName = playerName;
        stream.args = new String[]{battery+""};
        stream.send();
    }
    public void handcuffPlayer(IPlayer playerToHandcuff){
        DoApiStream stream = new DoApiStream();
        stream.playerName = playerName;
        stream.value = playerToHandcuff;
        stream.method = "HandcuffPlayer";
        stream.send();
    }
    public void removeHandcuffs(){
        DoStream stream = new DoStream();
        stream.playerName = playerName;
        stream.method = "RemoveHandcuffs";
        stream.send();

    }
    public boolean getGhostMode(){
        DoGetStream stream = new DoGetStream(Boolean.class);
        stream.method = "GetGhostMode";
        stream.playerName = playerName;
        return (Boolean) stream.send();
    }
    public void setGhostMode(boolean ghostMode, boolean visibleToSpec, boolean visibleWhenTalking){
        DoStream stream = new DoStream();
        stream.method = "SetGhostMode";
        stream.playerName = playerName;
        stream.args = new String[]{ghostMode+"",visibleToSpec+"",visibleWhenTalking+""};
        stream.send();
    }

}

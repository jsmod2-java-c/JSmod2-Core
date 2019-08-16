package cn.jsmod2.api.player;

import cn.jsmod2.api.item.IItem;
import cn.jsmod2.api.item.ItemType;
import cn.jsmod2.api.team.ITeamRole;
import cn.jsmod2.api.team.Role;
import cn.jsmod2.api.user.IUserGroup;
import cn.jsmod2.core.ISimplePlayer;
import cn.jsmod2.core.math.Vector;

import java.util.List;

public interface IPlayer extends ISimplePlayer {


    ITeamRole getTeamRole();

    void setTeamRole(ITeamRole teamRole);


    String getStreamId();


    RadioStatus getRadioStatus();

    void setRadioStatus(RadioStatus radioStatus);

    boolean isOverwatchMode();


    boolean isDoNotTrack();


    IScp079Data getScp079Data();


    void kill(DamageType type);

    void damage(int amount,DamageType type);

    void damage(int amount);

    void setHealth(int amount,DamageType type);

    void setHealth(int amount);

    int getAmmo(AmmoType type);

    void setAmmo(AmmoType type,int amount);

    Vector getPosition();

    void teleport(Vector pos,boolean unstuck);

    void teleport(Vector pos);

    void setRank(String color,String text,String group);

    String getRankName();

    void disConnect();

    void disConnect(String message);

    void ban(int duration);

    void ban(int duration,String message);

    IItem giveItem(ItemType type);

    List<? extends IItem> getInventory();

    IItem getCurrentItem();

    void setCurrentItem(ItemType type);

    int getCurrentItemIndex();

    void setCurrentItemIndex(int index);

    boolean hasItem(ItemType type);

    int getItemIndex(ItemType type);

    boolean isHandcuffed();

    void changeRole(Role role, boolean full, boolean spawnTeleport, boolean spawnProtected);

    Object getGameObject();

    IUserGroup getUserGroup();

    boolean runCommand(String command,String[] args);

    boolean getGodmode();

    void setGodmode(boolean godmode);

    Vector getRotation();

    void sendConsoleMessage(String message, String color);
    void sendConsoleMessage(String message);

    void infect(float time);
    void throwGrenade(GrenadeType grenadeType, boolean isCustomDirection, Vector direction, boolean isEnvironmentallyTriggered, Vector position, boolean isCustomForce, float throwForce, boolean slowThrow);
    void throwGrenade(GrenadeType grenadeType, boolean isCustomDirection, Vector direction, boolean isEnvironmentallyTriggered, Vector position, boolean isCustomForce, float throwForce);
    boolean getBypassMode();
    String getAuthToken();
    void hideTag(boolean enable);

    void personalBroadcast(int duration, String message, boolean isMonoSpaced);

    void personalClearBroadcasts();

    boolean hasPermission(String permissionName);

    Vector get106Portal();

    void setRadioBattery(int battery);

    void handcuffPlayer(IPlayer playerToHandcuff);

    void removeHandcuffs();

    boolean getGhostMode();

    void setGhostMode(boolean ghostMode, boolean visibleToSpec, boolean visibleWhenTalking);

}

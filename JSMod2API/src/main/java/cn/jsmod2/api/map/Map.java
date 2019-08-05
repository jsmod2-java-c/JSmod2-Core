/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.map;


import cn.jsmod2.api.item.Item;
import cn.jsmod2.api.item.ItemType;
import cn.jsmod2.api.player.Player;
import cn.jsmod2.api.team.Role;
import cn.jsmod2.core.math.Vector;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

//从map获取物品时，要为每个物品分配id(C#端分配),从而可以定位到
public class Map implements IMap, Serializable {

    private boolean warheadLeverEnabled;

    private boolean warheadKeycardEntered;

    private boolean warheadDetonated;
    private boolean LCZDecontaminated;

    public List<Item> getItems(ItemType type,boolean world_only){
        return null;
    }

    public Vector getRandomSpawnPoint(Role role){
        List<Vector> vectors = getSpawnPoints(role);
        return vectors.get(new Random().nextInt(vectors.size()));
    }

    public List<Vector> getSpawnPoints(Role role){
        return null;
    }

    public List<Vector> getBlastDoorPoints() {
        return null;
    }


    public List<Door> getDoors() {
        return null;
    }


    public List<PocketDimensionExit> getPocketDimensionExits() {
        return null;
    }


    public java.util.Map<Vector, Vector> getElevatorTeleportPoints() {
        return null;
    }


    public Generator[] getGenerators() {
        return new Generator[0];
    }


    public Room[] get079InteractionRooms(Scp079InteractionType type) {
        return new Room[0];
    }


    public void detonateWarhead() {

    }


    public void startWarhead() {

    }


    public void stopWarhead() {

    }


    public void shake() {

    }


    public void spawnItem(ItemType type, Vector position, Vector rotation) {

    }


    public void femurBreaker(boolean enable) {

    }


    public List<Elevator> getElevators() {
        return null;
    }


    public void setIntercomContent(IntercomStatus intercomStatus, String content) {

    }

    public String getIntercomContent(IntercomStatus intercomStatus) {
        return null;
    }


    public List<TeslaGate> getTeslaGates() {
        return null;
    }


    public void announceNtfEntrance(int scpsLeft, int mtfNumber, char mtfLetter) {

    }


    public void announceScpKill(String scpNumber, Player killer) {

    }


    public void announceCustomMessage(String words) {

    }


    public void setIntercomSpeaker(Player player) {

    }


    public Player getIntercomSpeaker() {
        return null;
    }


    public void broadcast(int duration, String message, boolean isMonoSpaced) {

    }


    public void clearBroadcasts() {

    }

    public boolean isWarheadLeverEnabled() {
        return warheadLeverEnabled;
    }

    public void setWarheadLeverEnabled(boolean warheadLeverEnabled) {
        this.warheadLeverEnabled = warheadLeverEnabled;
    }

    public boolean isWarheadKeycardEntered() {
        return warheadKeycardEntered;
    }

    public void setWarheadKeycardEntered(boolean warheadKeycardEntered) {
        this.warheadKeycardEntered = warheadKeycardEntered;
    }

    public boolean isWarheadDetonated() {
        return warheadDetonated;
    }

    public void setWarheadDetonated(boolean warheadDetonated) {
        this.warheadDetonated = warheadDetonated;
    }

    public boolean isLCZDecontaminated() {
        return LCZDecontaminated;
    }

    public void setLCZDecontaminated(boolean LCZDecontaminated) {
        this.LCZDecontaminated = LCZDecontaminated;
    }

    @Override
    public String toString() {
        return "Map{" +
                "warheadLeverEnabled=" + warheadLeverEnabled +
                ", warheadKeycardEntered=" + warheadKeycardEntered +
                ", warheadDetonated=" + warheadDetonated +
                ", LCZDecontaminated=" + LCZDecontaminated +
                '}';
    }
}

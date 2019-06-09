/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.map;

import cn.jsmod2.annotations.FieldInsert;
import cn.jsmod2.api.item.Item;
import cn.jsmod2.api.item.ItemType;
import cn.jsmod2.api.player.Player;
import cn.jsmod2.api.team.Role;
import cn.jsmod2.math.Vector;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Map{

    //全部物品
    @FieldInsert
    private List<Item> allItems;

    @FieldInsert
    private java.util.Map<Role, List<Vector>> randomSpawnPoints;



    private boolean warheadLeverEnabled;

    private boolean warheadKeycardEntered;

    public boolean warheadDetonated;
    public boolean LCZDecontaminated;

    //对于条件获得物品，将全部物品穷举出来，之后进行逻辑判断
    //C#端完成
    public List<Item> getItems(ItemType type,boolean world_only){
        return allItems.stream().filter(x->x.getItemType().equals(type)&&x.isInWord()==world_only).collect(Collectors.toList());
    }
    //随机getSpawnPoints方法的值
    private Vector getRandomSpawnPoint(Role role){
        List<Vector> vectors = getSpawnPoints(role);
        return vectors.get(new Random().nextInt(vectors.size()));
    }

    private List<Vector> getSpawnPoints(Role role){
        return randomSpawnPoints.get(role);
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
}

/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.map;



import cn.jsmod2.api.item.Item;
import cn.jsmod2.api.item.ItemType;
import cn.jsmod2.api.player.IPlayer;
import cn.jsmod2.api.player.Player;
import cn.jsmod2.api.team.Role;
import cn.jsmod2.core.ApiId;
import cn.jsmod2.core.math.Vector;
import cn.jsmod2.network.protocol.event.newstream.GetTypes;
import cn.jsmod2.network.protocol.map.map.SimpleMapFieldStream;
import cn.jsmod2.network.protocol.map.map.SimpleMapMethodPacket;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
//从map获取物品时，要为每个物品分配id(C#端分配),从而可以定位到
public class Map implements IMap, Serializable {

    private boolean warheadLeverEnabled;

    private boolean warheadKeycardEntered;

    private boolean warheadDetonated;
    private boolean LCZDecontaminated;

    @SuppressWarnings("unchecked")
    public List<Item> getItems(ItemType type, boolean world_only){
        SimpleMapMethodPacket packet = new SimpleMapMethodPacket(Item.class);
        packet.isWrite = false;
        packet.getTypes = GetTypes.GET_PROTOCOL_ARRAY_WITHOUT_LIST_IN;
        packet.method = "GetItems";
        packet.args = new String[]{"'"+type+"'",world_only+""};
        return (List)packet.send();
    }

    public Vector getRandomSpawnPoint(Role role){
        SimpleMapMethodPacket packet = new SimpleMapMethodPacket(Vector.class);
        packet.method = "GetRandomSpawnPoint";
        packet.isWrite = false;
        packet.getTypes = GetTypes.GET;
        packet.args = new String[]{"'"+role+"'"};
        return (Vector)packet.send();
    }

    @SuppressWarnings("unchecked")
    public List<Vector> getSpawnPoints(Role role){
        SimpleMapMethodPacket packet = new SimpleMapMethodPacket(Vector.class);
        packet.method = "GetSpawnPoints";
        packet.isWrite = false;
        packet.args = new String[]{"'"+role+"'"};
        packet.getTypes = GetTypes.GET_ARRAY;
        return (List)packet.send();
    }

    @SuppressWarnings("unchecked")
    public List<Vector> getBlastDoorPoints() {
        SimpleMapMethodPacket packet = new SimpleMapMethodPacket(Vector.class);
        packet.isWrite = false;
        packet.getTypes = GetTypes.GET_ARRAY;
        packet.method = "GetBlastDoorPoints";

        return (List<Vector>) packet.send();

    }

    @SuppressWarnings("unchecked")
    public List<IDoor> getDoors() {
        SimpleMapMethodPacket packet = new SimpleMapMethodPacket(Door.class);
        packet.isWrite = false;
        packet.method = "GetDoors";
        packet.getTypes = GetTypes.GET_PROTOCOL_ARRAY_WITHOUT_LIST_IN;
        return (List)packet.send();
    }

    @SuppressWarnings("unchecked")
    public List<PocketDimensionExit> getPocketDimensionExits() {
        SimpleMapMethodPacket packet = new SimpleMapMethodPacket(PocketDimensionExit.class);
        packet.isWrite = false;
        packet.method = "GetPocketDimensionExits";
        packet.getTypes = GetTypes.GET_PROTOCOL_ARRAY_WITHOUT_LIST_IN;
        return (List<PocketDimensionExit>) packet.send();
    }

    public java.util.Map<Vector, Vector> getElevatorTeleportPoints() {
        SimpleMapMethodPacket packet = new SimpleMapMethodPacket(Map.class);
        packet.isWrite = false;
        packet.method = "GetElevatorTeleportPoints";
        packet.getTypes = GetTypes.GET;
        java.util.Map<Vector,Vector> vectorMap = new HashMap<>();
        java.util.Map<String,String> map = (java.util.Map)packet.send();
        for(java.util.Map.Entry<String,String> entry:map.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            vectorMap.put(getVector(key),getVector(value));
        }
        return vectorMap;
    }

    private Vector getVector(String str){
        String[] xyz = str.substring(str.indexOf("(")+1,str.lastIndexOf(")")).split("-");
        double x = Double.parseDouble(xyz[0]);
        double y = Double.parseDouble(xyz[1]);
        double z = Double.parseDouble(xyz[2]);
        return new Vector(x,y,z);
    }


    public Generator[] getGenerators() {
        SimpleMapMethodPacket packet = new SimpleMapMethodPacket(Generator.class);
        packet.method = "GetGenerators";
        packet.isWrite = false;
        packet.getTypes = GetTypes.GET_PROTOCOL_ARRAY_WITHOUT_LIST_IN;
        return (Generator[]) ((List)packet.send()).toArray();
    }


    public Room[] get079InteractionRooms(Scp079InteractionType type) {
        SimpleMapMethodPacket packet = new SimpleMapMethodPacket(Room.class);
        packet.method = "Get079InteractionRooms";
        packet.getTypes = GetTypes.GET_PROTOCOL_ARRAY_WITHOUT_LIST_IN;
        packet.isWrite = false;
        packet.args = new String[]{"'"+type+"'"};
        return (Room[]) ((List)packet.send()).toArray();
    }


    public void detonateWarhead() {
        SimpleMapMethodPacket packet = new SimpleMapMethodPacket(Void.class);
        packet.isWrite = true;
        packet.method = "DetonateWarhead";
        packet.send();
    }


    public void startWarhead() {
        SimpleMapMethodPacket packet = new SimpleMapMethodPacket(Void.class);
        packet.isWrite = true;
        packet.method = "StartWarhead";
        packet.send();
    }


    public void stopWarhead() {
        SimpleMapMethodPacket packet = new SimpleMapMethodPacket(Void.class);
        packet.isWrite = true;
        packet.method = "StopWarhead";
        packet.send();
    }


    public void shake() {
        SimpleMapMethodPacket packet = new SimpleMapMethodPacket(Void.class);
        packet.isWrite = true;
        packet.method = "Shake";
        packet.send();
    }


    public void spawnItem(ItemType type, Vector position, Vector rotation) {
        SimpleMapMethodPacket packet = new SimpleMapMethodPacket(Void.class);
        packet.isWrite = true;
        packet.method = "SpawnItem";
        packet.args = new String[]{"'"+type+"'",position.toString(),rotation.toString()};
        packet.send();
    }


    public void femurBreaker(boolean enable) {
        SimpleMapMethodPacket packet = new SimpleMapMethodPacket(Void.class);
        packet.isWrite = true;
        packet.method = "FemurBreaker";
        packet.args = new String[]{enable+""};
        packet.send();
    }

    @SuppressWarnings("unchecked")
    public List<Elevator> getElevators() {
        SimpleMapMethodPacket packet = new SimpleMapMethodPacket(Elevator.class);
        packet.method = "GetElevators";
        packet.isWrite = false;
        packet.getTypes = GetTypes.GET_PROTOCOL_ARRAY_WITHOUT_LIST_IN;
        return (List<Elevator>) packet.send();
    }


    public void setIntercomContent(IntercomStatus intercomStatus, String content) {
        SimpleMapMethodPacket packet = new SimpleMapMethodPacket(Void.class);
        packet.method = "SetIntercomContent";
        packet.isWrite = true;
        packet.args = new String[]{"'"+intercomStatus+"'",content};
        packet.send();
    }

    public String getIntercomContent(IntercomStatus intercomStatus) {
        SimpleMapMethodPacket packet = new SimpleMapMethodPacket(String.class);
        packet.method = "GetIntercomContent";
        packet.args = new String[]{"'"+intercomStatus+"'"};
        packet.isWrite = false;
        packet.getTypes = GetTypes.GET;
        return (String) packet.send();
    }

    @SuppressWarnings("unchecked")
    public List<TeslaGate> getTeslaGates() {
        SimpleMapMethodPacket packet = new SimpleMapMethodPacket(TeslaGate.class);
        packet.method = "GetTeslaGates";
        packet.isWrite = false;
        packet.getTypes = GetTypes.GET_PROTOCOL_ARRAY_WITHOUT_LIST_IN;
        return (List<TeslaGate>) packet.send();
    }


    public void announceNtfEntrance(int scpsLeft, int mtfNumber, char mtfLetter) {
        SimpleMapMethodPacket packet = new SimpleMapMethodPacket(Void.class);
        packet.method = "AnnounceNtfEntrance";
        packet.isWrite = true;
        packet.args = new String[]{scpsLeft+"",mtfNumber+"",mtfLetter+""};
        packet.send();
    }


    public void announceScpKill(String scpNumber, IPlayer killer) {
        SimpleMapMethodPacket packet = new SimpleMapMethodPacket(Void.class);
        packet.method = "AnnounceScpKill";
        packet.isWrite = true;
        packet.args = new String[]{scpNumber,((ApiId)killer).getApiId()};
        packet.send();
    }


    public void announceCustomMessage(String words) {
        SimpleMapMethodPacket packet = new SimpleMapMethodPacket(Void.class);
        packet.method = "AnnounceCustomMessage";
        packet.isWrite = true;
        packet.args = new String[]{words};
        packet.send();
    }


    public void setIntercomSpeaker(IPlayer player) {
        SimpleMapMethodPacket packet = new SimpleMapMethodPacket(Void.class);
        packet.method = "SetIntercomSpeaker";
        packet.isWrite = true;
        packet.args = new String[]{((ApiId)player).getApiId()};
        packet.send();
    }


    public Player getIntercomSpeaker() {
        SimpleMapMethodPacket packet = new SimpleMapMethodPacket(Player.class);
        packet.method = "GetIntercomSpeaker";
        packet.isWrite = false;
        packet.getTypes = GetTypes.GET;
        return (Player) packet.send();
    }


    public void broadcast(int duration, String message, boolean isMonoSpaced) {
        SimpleMapMethodPacket packet = new SimpleMapMethodPacket(Void.class);
        packet.method = "Broadcast";
        packet.isWrite = true;
        packet.args = new String[]{duration+"",message,isMonoSpaced+""};
        packet.send();
    }


    public void clearBroadcasts() {
        SimpleMapMethodPacket packet = new SimpleMapMethodPacket(Void.class);
        packet.method = "ClearBroadcasts";
        packet.isWrite = true;
        packet.send();
    }

    public boolean isWarheadLeverEnabled() {
        SimpleMapFieldStream stream = new SimpleMapFieldStream(Boolean.class);
        stream.isWrite = false;
        stream.field = "WarheadLeverEnabled";
        warheadLeverEnabled = (Boolean) stream.send();
        return warheadLeverEnabled;
    }

    public void setWarheadLeverEnabled(boolean warheadLeverEnabled) {
        SimpleMapFieldStream stream = new SimpleMapFieldStream(Void.class);
        stream.field = "WarheadLeverEnabled";
        stream.isWrite = true;
        stream.value = warheadLeverEnabled;
        stream.send();
        this.warheadLeverEnabled = warheadLeverEnabled;
    }

    public boolean isWarheadKeycardEntered() {
        SimpleMapFieldStream stream = new SimpleMapFieldStream(Boolean.class);
        stream.isWrite = false;
        stream.field = "WarheadKeycardEntered";
        warheadKeycardEntered = (Boolean) stream.send();
        return warheadKeycardEntered;
    }

    public void setWarheadKeycardEntered(boolean warheadKeycardEntered) {
        SimpleMapFieldStream stream = new SimpleMapFieldStream(Void.class);
        stream.field = "WarheadKeycardEntered";
        stream.isWrite = true;
        stream.value = warheadKeycardEntered;
        stream.send();
        this.warheadKeycardEntered = warheadKeycardEntered;
    }

    public boolean isWarheadDetonated() {
        SimpleMapFieldStream stream = new SimpleMapFieldStream(Boolean.class);
        stream.isWrite = false;
        stream.field = "WarheadDetonated";
        warheadDetonated = (Boolean) stream.send();
        return warheadDetonated;
    }

    public void setWarheadDetonated(boolean warheadDetonated) {
        SimpleMapFieldStream stream = new SimpleMapFieldStream(Void.class);
        stream.field = "WarheadDetonated";
        stream.isWrite = true;
        stream.value = warheadDetonated;
        stream.send();
        this.warheadDetonated = warheadDetonated;
    }

    public boolean isLCZDecontaminated() {
        SimpleMapFieldStream stream = new SimpleMapFieldStream(Boolean.class);
        stream.isWrite = false;
        stream.field = "LCZDecontaminated";
        LCZDecontaminated = (Boolean) stream.send();
        return LCZDecontaminated;
    }

    public void setLCZDecontaminated(boolean LCZDecontaminated) {
        SimpleMapFieldStream stream = new SimpleMapFieldStream(Void.class);
        stream.field = "LCZDecontaminated";
        stream.isWrite = true;
        stream.value = LCZDecontaminated;
        stream.send();
        this.LCZDecontaminated = LCZDecontaminated;
    }

}

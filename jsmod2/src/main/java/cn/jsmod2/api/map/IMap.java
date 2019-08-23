package cn.jsmod2.api.map;

import cn.jsmod2.api.item.IItem;
import cn.jsmod2.api.item.ItemType;
import cn.jsmod2.api.player.IPlayer;
import cn.jsmod2.api.team.Role;
import cn.jsmod2.core.math.Vector;

import java.util.List;


public interface IMap {

    List<? extends IItem> getItems(ItemType type, boolean world_only);

    Vector getRandomSpawnPoint(Role role);

    List<Vector> getSpawnPoints(Role role);

    List<Vector> getBlastDoorPoints();


    List<? extends IDoor> getDoors();


    List<? extends IPocketDimensionExit> getPocketDimensionExits();


    java.util.Map<Vector, Vector> getElevatorTeleportPoints();


    IGenerator[] getGenerators();


    IRoom[] get079InteractionRooms(Scp079InteractionType type);


    void detonateWarhead();


    void startWarhead();


    void stopWarhead();


    void shake();


    void spawnItem(ItemType type, Vector position, Vector rotation);


    void femurBreaker(boolean enable);


    List<? extends IElevator> getElevators();


    void setIntercomContent(IntercomStatus intercomStatus, String content);

    String getIntercomContent(IntercomStatus intercomStatus);


    List<? extends ITeslaGate> getTeslaGates();


    void announceNtfEntrance(int scpsLeft, int mtfNumber, char mtfLetter);


    void announceScpKill(String scpNumber, IPlayer killer);

    void announceCustomMessage(String words);


    void setIntercomSpeaker(IPlayer player);


    IPlayer getIntercomSpeaker();


    void broadcast(int duration, String message, boolean isMonoSpaced);


    void clearBroadcasts();

    boolean isWarheadLeverEnabled();

    void setWarheadLeverEnabled(boolean warheadLeverEnabled);

    boolean isWarheadKeycardEntered();

    void setWarheadKeycardEntered(boolean warheadKeycardEntered);

    boolean isWarheadDetonated();

    void setWarheadDetonated(boolean warheadDetonated);

    boolean isLCZDecontaminated();

    void setLCZDecontaminated(boolean LCZDecontaminated);


}

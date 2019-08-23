package cn.jsmod2.api.player;

import cn.jsmod2.api.map.IDoor;
import cn.jsmod2.api.map.IRoom;
import cn.jsmod2.api.map.ITeslaGate;
import cn.jsmod2.core.math.Vector;

public interface IScp079Data {

    float getExp();

    void setExp(float exp);

    int getExpToLevelUp();

    void setExpToLevelUp(int expToLevelUp);

    int getLevel();

    void setLevel(int level);

    float getAP();

    void setAP(float AP);

    float getAPPerSecond();

    void setAPPerSecond(float APPerSecond);

    float getMaxAP();

    void setMaxAP(float maxAP);

    float getSpeakerAPPerSecond();

    void setSpeakerAPPerSecond(float speakerAPPerSecond);

    float getYaw();


    float getPitch();

    IRoom getSpeaker();

    void setSpeaker(IRoom speaker);

    Vector getCamera();

    IDoor[] getLockedDoors();

    void lock(IDoor door);

    void unlock(IDoor door);

    void triggerTesla(ITeslaGate tesla);

    void lockdown(IRoom room);

    void setCamera(Vector position, boolean lookAt);



    void showGainExp(ExperienceType expType);

    void showLevelUp(int level);

    Object getComponent();
}

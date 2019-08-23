package cn.jsmod2.api.map;

import cn.jsmod2.core.math.Vector;

public interface IRoom {

    void flickerLights();

    ZoneType getZoneType();

    RoomType getRoomType();



    int getGenericID();



    Vector getPosition();



    Vector getForward();

    Vector getSpeakerPosition();

    String[] getObjectName();

    Object getGameObject();
}

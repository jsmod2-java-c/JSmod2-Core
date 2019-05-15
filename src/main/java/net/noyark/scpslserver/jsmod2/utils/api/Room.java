package net.noyark.scpslserver.jsmod2.utils.api;

import net.noyark.scpslserver.jsmod2.math.SCPVector;

/**
 * @author magiclu550
 */

public class Room {

    private ZoneType zoneType;
    private RoomType roomType;
    private int genericID;
    private SCPVector position;
    private SCPVector forward;
    private SCPVector speakerPosition;

    public void flickerLights(){

    }

    public ZoneType getZoneType() {
        return zoneType;
    }



    public RoomType getRoomType() {
        return roomType;
    }



    public int getGenericID() {
        return genericID;
    }



    public SCPVector getPosition() {
        return position;
    }



    public SCPVector getForward() {
        return forward;
    }

    public SCPVector getSpeakerPosition() {
        return speakerPosition;
    }

    public String[] getObjectName(){
        return null;
    }
    public Object getGameObject(){
        return null;
    }
}

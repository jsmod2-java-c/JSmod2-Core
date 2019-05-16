package net.noyark.scpslserver.jsmod2.utils.api;

import net.noyark.scpslserver.jsmod2.math.Vector;

/**
 * @author magiclu550
 */

public class Room {

    private ZoneType zoneType;
    private RoomType roomType;
    private int genericID;
    private Vector position;
    private Vector forward;
    private Vector speakerPosition;

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



    public Vector getPosition() {
        return position;
    }



    public Vector getForward() {
        return forward;
    }

    public Vector getSpeakerPosition() {
        return speakerPosition;
    }

    public String[] getObjectName(){
        return null;
    }
    public Object getGameObject(){
        return null;
    }
}

/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.map;

import cn.jsmod2.core.ApiId;
import cn.jsmod2.core.math.Vector;


/**
 * @author magiclu550
 */

public class Room extends ApiId {

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

    @Override
    public String toString() {
        return "Room{" +
                "zoneType=" + zoneType +
                ", roomType=" + roomType +
                ", genericID=" + genericID +
                ", position=" + position +
                ", forward=" + forward +
                ", speakerPosition=" + speakerPosition +
                '}';
    }
}

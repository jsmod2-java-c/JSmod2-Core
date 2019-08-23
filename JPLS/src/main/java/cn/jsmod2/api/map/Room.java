/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.map;

import cn.jsmod2.core.ApiId;
import cn.jsmod2.core.math.Vector;
import cn.jsmod2.network.DoGetStream;
import cn.jsmod2.network.DoStream;
import cn.jsmod2.network.SimpleGetStream;
import cn.jsmod2.network.protocol.event.newstream.GetTypes;

import java.io.Serializable;
import java.util.List;


/**
 * @author magiclu550
 */

public class Room extends ApiId implements IRoom, Serializable,Cloneable {

    private ZoneType zoneType;
    private RoomType roomType;
    private int genericID;
    private Vector position;
    private Vector forward;
    private Vector speakerPosition;

    public void flickerLights(){
        DoStream stream = new DoStream();
        stream.method = "FlickerLights";
        stream.playerName = playerName;
        stream.send();
    }

    public ZoneType getZoneType() {
        SimpleGetStream stream = new SimpleGetStream(ZoneType.class);
        zoneType = stream.read(playerName,"ZoneType",ZoneType.class);
        return zoneType;
    }



    public RoomType getRoomType() {
        SimpleGetStream stream = new SimpleGetStream(RoomType.class);
        roomType = stream.read(playerName,"RoomType",RoomType.class);
        return roomType;
    }



    public int getGenericID() {
        SimpleGetStream stream = new SimpleGetStream(Integer.class);
        genericID = stream.read(playerName,"GenericID",Integer.class);
        return genericID;
    }


    public Vector getPosition() {
        SimpleGetStream stream = new SimpleGetStream(Vector.class);
        position = stream.read(playerName,"Position",Vector.class);
        return position;
    }



    public Vector getForward() {
        SimpleGetStream stream = new SimpleGetStream(Vector.class);
        forward = stream.read(playerName,"Forward",Vector.class);
        return forward;
    }

    public Vector getSpeakerPosition() {
        SimpleGetStream stream = new SimpleGetStream(Vector.class);
        speakerPosition = stream.read(playerName,"SpeakerPosition",Vector.class);
        return speakerPosition;
    }

    public String[] getObjectName(){
        DoGetStream stream = new DoGetStream(String.class);
        stream.playerName = playerName;
        stream.getType = GetTypes.GET_ARRAY;
        stream.method = "GetObjectName";
        return (String[]) ((List)stream.send()).toArray();
    }

    //这里未来解决
    public Object getGameObject(){
        return null;
    }


}

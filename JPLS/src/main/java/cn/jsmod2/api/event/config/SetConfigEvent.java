/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.config;


import cn.jsmod2.core.event.Event;
import cn.jsmod2.network.protocol.event.newstream.EventValueGetStream;
import cn.jsmod2.network.protocol.event.newstream.EventValueSetStream;

import static cn.jsmod2.network.PacketSender.sendGetPacket;
import static cn.jsmod2.network.PacketSender.sendSetPacket;

/**
 * @author magiclu550 #(code) jsmod2
 */


public class SetConfigEvent extends Event implements ISetConfigEvent {

    private String key;

    private Object value;

    private Object defaultValue;


    public SetConfigEvent(){

    }

    public String getKey() {
        EventValueGetStream stream = new EventValueGetStream(String.class);
        stream.playerName = playerName;
        stream.name = "Key";
        key = sendGetPacket(stream,String.class);
        return key;
    }

    public void setKey(String key) {
        EventValueSetStream stream = new EventValueSetStream();
        stream.playerName = playerName;
        stream.name = "Key";
        stream.value = key;
        sendSetPacket(stream);
        this.key = key;
    }

    public <T> T getValue(Class<T> type) {
        EventValueGetStream stream = new EventValueGetStream(type);
        stream.playerName = playerName;
        stream.name = "Value";
        value = sendGetPacket(stream,type);
        return type.cast(value);
    }

    public void setValue(Object value) {
        EventValueSetStream stream = new EventValueSetStream();
        stream.playerName = playerName;
        stream.name = "Value";
        stream.value = value;
        sendSetPacket(stream);
        this.value = value;
    }

    public <T> T getDefaultValue(Class<T> type) {
        EventValueGetStream stream = new EventValueGetStream(type);
        stream.playerName = playerName;
        stream.name = "DefaultValue";
        defaultValue = sendGetPacket(stream,type);
        return type.cast(defaultValue);
    }



}

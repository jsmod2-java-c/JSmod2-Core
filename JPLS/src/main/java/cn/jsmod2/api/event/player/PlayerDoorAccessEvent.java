package cn.jsmod2.api.event.player;

import cn.jsmod2.api.map.Door;
import cn.jsmod2.network.PacketSender;

public class PlayerDoorAccessEvent extends PlayerEvent implements IPlayerDoorAccessEvent {

    private boolean allow;

    private boolean destroy;

    private Door door;

    public boolean isAllow() {
        allow = PacketSender.sendEventGetPacket(playerName,"Allow",Boolean.class);
        return allow;
    }

    public void setAllow(boolean allow) {
        PacketSender.sendEventSetPacket(playerName,"Allow",allow);
        this.allow = allow;
    }

    public boolean isDestroy() {
        destroy = PacketSender.sendEventGetPacket(playerName,"Destroy",Boolean.class);
        return destroy;
    }

    public void setDestroy(boolean destroy) {
        PacketSender.sendEventSetPacket(playerName,"Destroy",destroy);
        this.destroy = destroy;
    }

    public Door getDoor() {
        return door;
    }

}

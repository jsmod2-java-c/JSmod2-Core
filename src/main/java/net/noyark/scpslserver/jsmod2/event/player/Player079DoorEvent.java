package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;

public class Player079DoorEvent extends PlayerEvent {
    private Door door;
    private boolean allow;
    private float apDrain;

    public Player079DoorEvent(Player player, Door door, boolean allow, float apDrain) {
        super(player);
        this.door = door;
        this.allow = allow;
        this.apDrain = apDrain;
    }

    public Door getDoor() {
        return door;
    }

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }

    public float getApDrain() {
        return apDrain;
    }

    public void setApDrain(float apDrain) {
        this.apDrain = apDrain;
    }
}

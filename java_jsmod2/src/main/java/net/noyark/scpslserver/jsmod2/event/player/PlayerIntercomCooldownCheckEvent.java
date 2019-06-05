package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;

public class PlayerIntercomCooldownCheckEvent extends PlayerEvent{
    private float currentCooldown;

    public PlayerIntercomCooldownCheckEvent(Player player, float currentCooldown) {
        super(player);
        this.currentCooldown = currentCooldown;
    }

    public PlayerIntercomCooldownCheckEvent(){

    }

    public float getCurrentCooldown() {
        return currentCooldown;
    }

    public void setCurrentCooldown(float currentCooldown) {
        this.currentCooldown = currentCooldown;
    }
}

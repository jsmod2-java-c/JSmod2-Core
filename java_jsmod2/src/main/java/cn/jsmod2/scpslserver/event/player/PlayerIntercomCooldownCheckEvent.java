package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.entity.Player;

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

package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;

public class PlayerIntercomEvent extends PlayerEvent {
    private float speechTime;
    private float cooldownTime;

    public float getSpeechTime() {
        return speechTime;
    }

    public void setSpeechTime(float speechTime) {
        this.speechTime = speechTime;
    }

    public float getCooldownTime() {
        return cooldownTime;
    }

    public void setCooldownTime(float cooldownTime) {
        this.cooldownTime = cooldownTime;
    }

    public PlayerIntercomEvent(Player player, float speechTime, float cooldownTime) {
        super(player);
        this.speechTime = speechTime;
        this.cooldownTime = cooldownTime;
    }
}

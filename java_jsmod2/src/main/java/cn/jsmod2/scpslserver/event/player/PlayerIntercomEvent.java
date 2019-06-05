package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.entity.Player;

/**
 * @author kevinj
 */

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

    public PlayerIntercomEvent(){

    }
}

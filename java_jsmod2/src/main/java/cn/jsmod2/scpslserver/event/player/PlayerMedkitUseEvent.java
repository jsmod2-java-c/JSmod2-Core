package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.entity.Player;

/**
 * @author kevinj
 */

public class PlayerMedkitUseEvent extends PlayerEvent {
    private Integer recoverHealth;

    public PlayerMedkitUseEvent(Player player, Integer recoverHealth) {
        super(player);
        this.recoverHealth = recoverHealth;
    }

    public PlayerMedkitUseEvent(){

    }

    public Integer getRecoverHealth() {
        return recoverHealth;
    }

    public void setRecoverHealth(Integer recoverHealth) {
        this.recoverHealth = recoverHealth;
    }


}

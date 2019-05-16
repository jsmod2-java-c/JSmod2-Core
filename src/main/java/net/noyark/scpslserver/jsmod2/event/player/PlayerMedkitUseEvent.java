package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;

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

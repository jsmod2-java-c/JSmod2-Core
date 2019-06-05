package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.utils.player.RadioStatus;
/**
 * @author kevinj
 */
public class PlayerRadioSwitchEvent extends PlayerEvent{
    private RadioStatus ChangeTo;

    public RadioStatus getChangeTo() {
        return ChangeTo;
    }

    public void setChangeTo(RadioStatus changeTo) {
        ChangeTo = changeTo;
    }



    public PlayerRadioSwitchEvent(Player player, RadioStatus changeTo) {
        super(player);
        ChangeTo = changeTo;
    }

    public PlayerRadioSwitchEvent(){

    }
}

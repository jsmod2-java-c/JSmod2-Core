package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.utils.player.RadioStatus;
import cn.jsmod2.scpslserver.entity.Player;

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

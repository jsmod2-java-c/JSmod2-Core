package net.noyark.scpslserver.jsmod2.event.player;


import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.utils.item.KnobSetting;
/**
 * @author kevinj
 */
public class PlayerSCP914ChangeKnobEvent extends PlayerEvent {
    private KnobSetting knobSetting;

    public KnobSetting getKnobSetting() {
        return knobSetting;
    }

    public void setKnobSetting(KnobSetting knobSetting) {
        this.knobSetting = knobSetting;
    }

    public PlayerSCP914ChangeKnobEvent(){

    }

    public PlayerSCP914ChangeKnobEvent(Player player, KnobSetting knobSetting) {
        super(player);
        this.knobSetting = knobSetting;
    }
}

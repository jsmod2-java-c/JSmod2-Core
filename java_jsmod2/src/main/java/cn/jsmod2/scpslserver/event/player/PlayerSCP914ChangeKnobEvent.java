package cn.jsmod2.scpslserver.event.player;


import cn.jsmod2.scpslserver.utils.item.KnobSetting;
import cn.jsmod2.scpslserver.entity.Player;

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

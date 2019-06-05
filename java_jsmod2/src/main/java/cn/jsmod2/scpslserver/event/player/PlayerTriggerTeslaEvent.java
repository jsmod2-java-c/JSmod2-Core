package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.entity.TeslaGate;
import cn.jsmod2.scpslserver.entity.Player;

/**
 * @author kevinj
 */

public class PlayerTriggerTeslaEvent extends PlayerEvent {
    private cn.jsmod2.scpslserver.entity.TeslaGate TeslaGate;
    private boolean Triggerable;

    public PlayerTriggerTeslaEvent(Player player, TeslaGate teslaGate, boolean triggerable) {
        super(player);
        TeslaGate = teslaGate;
        Triggerable = triggerable;
    }

    public PlayerTriggerTeslaEvent(){

    }

    public void setTriggerable(boolean triggerable) {
        Triggerable = triggerable;
    }

    public TeslaGate getTeslaGate() {
        return TeslaGate;
    }

    public boolean isTriggerable() {
        return Triggerable;
    }

    /** java-bean */
    @UseForServerInit
    public void setTeslaGate(TeslaGate teslaGate) {
        TeslaGate = teslaGate;
    }
}

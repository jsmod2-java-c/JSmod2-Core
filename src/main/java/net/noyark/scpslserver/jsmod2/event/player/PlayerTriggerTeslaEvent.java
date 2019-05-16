package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;

public class PlayerTriggerTeslaEvent extends PlayerEvent {
    public TeslaGate TeslaGate;
    public boolean Triggerable;

    public PlayerTriggerTeslaEvent(Player player, TeslaGate teslaGate, boolean triggerable) {
        super(player);
        TeslaGate = teslaGate;
        Triggerable = triggerable;
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
}

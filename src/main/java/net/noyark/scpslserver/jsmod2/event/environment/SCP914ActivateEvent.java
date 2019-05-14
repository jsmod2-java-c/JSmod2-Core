package net.noyark.scpslserver.jsmod2.event.environment;

import net.noyark.scpslserver.jsmod2.entity.KnobSetting;
import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.event.Event;
import net.noyark.scpslserver.jsmod2.math.SCPVector;


/**
 * @author magiclu550 #(code) jsmod2
 * @author kevinj
 */


public class SCP914ActivateEvent extends Event {

    private Player user;

    private KnobSetting knobSetting;

    public Object[] inputs;

    public SCPVector intakePos;

    public SCPVector outputPos;

    public SCP914ActivateEvent(Player user, KnobSetting knobSetting, Object[] inputs, SCPVector intakePos, SCPVector outputPos) {
        this.user = user;
        this.knobSetting = knobSetting;
        this.inputs = inputs;
        this.intakePos = intakePos;
        this.outputPos = outputPos;
    }

    public Player getUser() {
        return user;
    }

    public void setUser(Player user) {
        this.user = user;
    }

    public KnobSetting getKnobSetting() {
        return knobSetting;
    }

    public void setKnobSetting(KnobSetting knobSetting) {
        this.knobSetting = knobSetting;
    }

    public Object[] getInputs() {
        return inputs;
    }

    public void setInputs(Object[] inputs) {
        this.inputs = inputs;
    }

    public SCPVector getIntakePos() {
        return intakePos;
    }

    public void setIntakePos(SCPVector intakePos) {
        this.intakePos = intakePos;
    }

    public SCPVector getOutputPos() {
        return outputPos;
    }

    public void setOutputPos(SCPVector outputPos) {
        this.outputPos = outputPos;
    }

}

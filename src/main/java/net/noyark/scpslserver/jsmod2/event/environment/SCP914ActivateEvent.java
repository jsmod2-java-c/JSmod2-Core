package net.noyark.scpslserver.jsmod2.event.environment;

import net.noyark.scpslserver.jsmod2.utils.item.KnobSetting;
import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.event.Event;
import net.noyark.scpslserver.jsmod2.math.Vector;


/**
 * @author magiclu550 #(code) jsmod2
 * @author kevinj
 */


public class SCP914ActivateEvent extends Event {

    private Player user;

    private KnobSetting knobSetting;

    private Object[] inputs;

    private Vector intakePos;

    private Vector outputPos;

    public SCP914ActivateEvent(Player user, KnobSetting knobSetting, Object[] inputs, Vector intakePos, Vector outputPos) {
        this.user = user;
        this.knobSetting = knobSetting;
        this.inputs = inputs;
        this.intakePos = intakePos;
        this.outputPos = outputPos;
    }

    public SCP914ActivateEvent(){

    }

    public Player getUser() {
        return user;
    }


    /** java-bean */
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

    public Vector getIntakePos() {
        return intakePos;
    }

    public void setIntakePos(Vector intakePos) {
        this.intakePos = intakePos;
    }

    public Vector getOutputPos() {
        return outputPos;
    }

    public void setOutputPos(Vector outputPos) {
        this.outputPos = outputPos;
    }

}

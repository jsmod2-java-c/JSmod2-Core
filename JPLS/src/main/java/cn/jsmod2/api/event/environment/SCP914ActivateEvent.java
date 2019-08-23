/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.environment;

import cn.jsmod2.api.item.KnobSetting;
import cn.jsmod2.api.player.Player;

import cn.jsmod2.core.event.Event;
import cn.jsmod2.core.math.Vector;
import cn.jsmod2.network.PacketSender;
import cn.jsmod2.network.protocol.event.newstream.GetTypes;
import com.alibaba.fastjson.JSONObject;

import static cn.jsmod2.network.PacketSender.sendEventGetPacket;
import static cn.jsmod2.network.PacketSender.sendEventSetPacket;


/**
 * @author magiclu550 #(code) jsmod2
 * @author kevinj
 */


public class SCP914ActivateEvent extends Event implements ISCP914ActivateEvent{

    private Player user = new Player("");

    private KnobSetting knobSetting;

    private Object[] inputs;

    private Vector intakePos;

    private Vector outputPos;



    public SCP914ActivateEvent(){

    }

    public Player getUser() {
        return user;
    }



    public KnobSetting getKnobSetting() {
        sendEventGetPacket(playerName,"KnobSetting",KnobSetting.class);
        return knobSetting;
    }

    public void setKnobSetting(KnobSetting knobSetting) {
        sendEventSetPacket(playerName,"KnobSetting",knobSetting);
        this.knobSetting = knobSetting;
    }

    //这里未来解决
    public Object[] getInputs() {
        inputs = sendEventGetPacket(playerName,"Inputs", JSONObject.class,JSONObject[].class, GetTypes.GET_ARRAY);
        return inputs;
    }

    //这里未来解决
    public void setInputs(Object[] inputs) {
        sendEventSetPacket(playerName,"Inputs",inputs);
        this.inputs = inputs;
    }

    public Vector getIntakePos() {
        intakePos = sendEventGetPacket(playerName,"IntakePos",Vector.class);
        return intakePos;
    }

    public void setIntakePos(Vector intakePos) {
        sendEventSetPacket(playerName,"IntakePos",intakePos);
        this.intakePos = intakePos;
    }

    public Vector getOutputPos() {
        outputPos = sendEventGetPacket(playerName,"OutputPos",Vector.class);
        return outputPos;
    }

    public void setOutputPos(Vector outputPos) {
        sendEventSetPacket(playerName,"OutputPos",outputPos);
        this.outputPos = outputPos;
    }

}

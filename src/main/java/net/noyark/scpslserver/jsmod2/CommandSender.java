package net.noyark.scpslserver.jsmod2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Player,Console is command sender
 * @author magiclu550 #(code) jsmod2
 */

public abstract class CommandSender {

    private List<String> powers = new LinkedList<>();//powers

    private String senderName;

    protected CommandSender(String senderName,String... defaultPowers){
        this.senderName = senderName;
        this.powers.addAll(Arrays.asList(defaultPowers));
    }

    public String getSenderName() {
        return senderName;
    }

    public List<String> getPowers(){
        return powers;
    }
}

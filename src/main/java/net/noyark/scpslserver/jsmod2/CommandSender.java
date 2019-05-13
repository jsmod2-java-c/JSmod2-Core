package net.noyark.scpslserver.jsmod2;

/**
 * Player,Console is command sender
 * @author magiclu550 #(code) jsmod2
 */

public abstract class CommandSender {

    private String senderName;

    protected CommandSender(String senderName){
        this.senderName = senderName;
    }

    public String getSenderName() {
        return senderName;
    }
}

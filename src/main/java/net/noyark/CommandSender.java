package net.noyark;

public abstract class CommandSender {

    private String senderName;

    protected CommandSender(String senderName){
        this.senderName = senderName;
    }

    public String getSenderName() {
        return senderName;
    }
}

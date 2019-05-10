package net.noyark.scpslserver.jsmod2.event.admin;

import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.event.Event;

public class BanEvent extends Event {

    private Player player;

    private Player admin;

    private int duration;

    private String reason;

    private String result;

    private boolean allowBan;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getAdmin() {
        return admin;
    }

    public void setAdmin(Player admin) {
        this.admin = admin;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isAllowBan() {
        return allowBan;
    }

    public void setAllowBan(boolean allowBan) {
        this.allowBan = allowBan;
    }
}

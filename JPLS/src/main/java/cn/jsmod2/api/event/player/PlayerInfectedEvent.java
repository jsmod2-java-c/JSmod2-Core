package cn.jsmod2.api.event.player;

import cn.jsmod2.api.player.IPlayer;
import cn.jsmod2.api.player.Player;
import cn.jsmod2.network.PacketSender;

public class PlayerInfectedEvent extends PlayerEvent implements IPlayerInfectedEvent{

    private float damage;

    private Player attacker;

    private float infectTime;

    @Override
    public float getDamage() {
        damage = PacketSender.sendEventGetPacket(playerName,"Damage",Float.class);
        return damage;
    }

    @Override
    public void setDamage(float v) {
        PacketSender.sendEventSetPacket(playerName,"Damage",v);
        this.infectTime = v;
    }

    @Override
    public IPlayer getAttacker() {
        return attacker;
    }

    @Override
    public float getInfectTime() {
        infectTime = PacketSender.sendEventGetPacket(playerName,"InfectTime",Float.class);
        return infectTime;
    }

    @Override
    public void setInfectTime(float v) {
        PacketSender.sendEventSetPacket(playerName,"InfectTime",v);
        this.damage = v;
    }
}

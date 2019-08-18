/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.team;

import cn.jsmod2.core.event.Event;
import cn.jsmod2.network.PacketSender;

public class SetSCPConfigEvent extends Event implements ISetSCPConfigEvent{

    private boolean ban049;

    private boolean ban079;

    private boolean ban096;

    private boolean ban106;

    private boolean ban173;

    private boolean ban939_53;

    private boolean ban939_89;

    private int SCP049amount;

    private int SCP079amount;

    private int SCP096amount;

    private int SCP106amount;

    private int SCP173amount;

    private int SCP457amount;

    private int SCP939_53amount;

    private int SCP939_89amount;


    public SetSCPConfigEvent(boolean ban049, boolean ban079, boolean ban096, boolean ban106, boolean ban173, boolean ban939_53, boolean ban939_89, int SCP049amount, int SCP079amount, int SCP096amount, int SCP106amount, int SCP173amount, int SCP457amount, int SCP939_53amount, int SCP939_89amount) {
        this.ban049 = ban049;
        this.ban079 = ban079;
        this.ban096 = ban096;
        this.ban106 = ban106;
        this.ban173 = ban173;
        this.ban939_53 = ban939_53;
        this.ban939_89 = ban939_89;
        this.SCP049amount = SCP049amount;
        this.SCP079amount = SCP079amount;
        this.SCP096amount = SCP096amount;
        this.SCP106amount = SCP106amount;
        this.SCP173amount = SCP173amount;
        this.SCP457amount = SCP457amount;
        this.SCP939_53amount = SCP939_53amount;
        this.SCP939_89amount = SCP939_89amount;
    }

    public SetSCPConfigEvent(){

    }
    public boolean isBan049() {
        ban049 = PacketSender.sendEventGetPacket(playerName,"Ban049",Boolean.class);
        return ban049;
    }

    public void setBan049(boolean ban049) {
        PacketSender.sendEventSetPacket(playerName,"Ban049",ban049);
        this.ban049 = ban049;
    }

    public boolean isBan079() {
        ban079 = PacketSender.sendEventGetPacket(playerName,"Ban079",Boolean.class);
        return ban079;
    }

    public void setBan079(boolean ban079) {
        PacketSender.sendEventSetPacket(playerName,"Ban079",ban079);
        this.ban079 = ban079;
    }

    public boolean isBan096() {
        ban096 = PacketSender.sendEventGetPacket(playerName,"Ban096",Boolean.class);
        return ban096;
    }

    public void setBan096(boolean ban096) {
        PacketSender.sendEventSetPacket(playerName,"Ban096",ban096);
        this.ban096 = ban096;
    }

    public boolean isBan106() {
        ban106 = PacketSender.sendEventGetPacket(playerName,"Ban106",Boolean.class);
        return ban106;
    }

    public void setBan106(boolean ban106) {
        PacketSender.sendEventSetPacket(playerName,"Ban106",ban106);
        this.ban106 = ban106;
    }

    public boolean isBan173() {
        ban173 = PacketSender.sendEventGetPacket(playerName,"Ban173",Boolean.class);
        return ban173;
    }

    public void setBan173(boolean ban173) {
        PacketSender.sendEventSetPacket(playerName,"Ban173",ban173);
        this.ban173 = ban173;
    }

    public boolean isBan939_53() {
        ban939_53 = PacketSender.sendEventGetPacket(playerName,"Ban939_53",Boolean.class);
        return ban939_53;
    }

    public void setBan939_53(boolean ban939_53) {
        PacketSender.sendEventSetPacket(playerName,"Ban939_53",ban939_53);
        this.ban939_53 = ban939_53;
    }

    public boolean isBan939_89() {
        ban939_89 = PacketSender.sendEventGetPacket(playerName,"Ban939_89",Boolean.class);
        return ban939_89;
    }

    public void setBan939_89(boolean ban939_89) {
        PacketSender.sendEventSetPacket(playerName,"Ban939_89",ban939_89);
        this.ban939_89 = ban939_89;
    }

    public int getSCP049amount() {
        SCP049amount = PacketSender.sendEventGetPacket(playerName,"SCP049amount",Integer.class);
        return SCP049amount;
    }

    public void setSCP049amount(int SCP049amount) {
        PacketSender.sendEventSetPacket(playerName,"SCP049amount",SCP049amount);
        this.SCP049amount = SCP049amount;
    }

    public int getSCP079amount() {
        SCP079amount = PacketSender.sendEventGetPacket(playerName,"SCP079amount",Integer.class);
        return SCP079amount;
    }

    public void setSCP079amount(int SCP079amount) {
        PacketSender.sendEventSetPacket(playerName,"SCP079amount",SCP079amount);
        this.SCP079amount = SCP079amount;
    }

    public int getSCP096amount() {
        SCP096amount = PacketSender.sendEventGetPacket(playerName,"SCP096amount",Integer.class);
        return SCP096amount;
    }

    public void setSCP096amount(int SCP096amount) {
        PacketSender.sendEventSetPacket(playerName,"SCP096amount",SCP096amount);
        this.SCP096amount = SCP096amount;
    }

    public int getSCP106amount() {
        SCP106amount = PacketSender.sendEventGetPacket(playerName,"SCP106amount",Integer.class);
        return SCP106amount;
    }

    public void setSCP106amount(int SCP106amount) {
        PacketSender.sendEventSetPacket(playerName,"SCP106amount",SCP106amount);
        this.SCP106amount = SCP106amount;
    }

    public int getSCP173amount() {
        SCP173amount = PacketSender.sendEventGetPacket(playerName,"SCP173amount",Integer.class);
        return SCP173amount;
    }

    public void setSCP173amount(int SCP173amount) {
        PacketSender.sendEventSetPacket(playerName,"SCP173amount",SCP173amount);
        this.SCP173amount = SCP173amount;
    }

    public int getSCP457amount() {
        SCP457amount = PacketSender.sendEventGetPacket(playerName,"SCP457amount",Integer.class);
        return SCP457amount;
    }

    public void setSCP457amount(int SCP457amount) {
        PacketSender.sendEventSetPacket(playerName,"SCP457amount",SCP457amount);
        this.SCP457amount = SCP457amount;
    }

    public int getSCP939_53amount() {
        PacketSender.sendEventGetPacket(playerName,"SCP939_53amount",Integer.class);
        return SCP939_53amount;
    }

    public void setSCP939_53amount(int SCP939_53amount) {
        PacketSender.sendEventSetPacket(playerName,"SCP939_53amount",SCP939_53amount);
        this.SCP939_53amount = SCP939_53amount;
    }

    public int getSCP939_89amount() {
        SCP939_89amount = PacketSender.sendEventGetPacket(playerName,"SCP939_89amount",Integer.class);
        return SCP939_89amount;
    }

    public void setSCP939_89amount(int SCP939_89amount) {
        PacketSender.sendEventSetPacket(playerName,"SCP939_89amount",SCP939_89amount);
        this.SCP939_89amount = SCP939_89amount;
    }
}

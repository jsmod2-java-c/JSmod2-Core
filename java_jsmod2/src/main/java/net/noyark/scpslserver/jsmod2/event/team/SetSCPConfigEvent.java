package net.noyark.scpslserver.jsmod2.event.team;

import net.noyark.scpslserver.jsmod2.event.Event;

public class SetSCPConfigEvent extends Event {

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
        return ban049;
    }

    public void setBan049(boolean ban049) {
        this.ban049 = ban049;
    }

    public boolean isBan079() {
        return ban079;
    }

    public void setBan079(boolean ban079) {
        this.ban079 = ban079;
    }

    public boolean isBan096() {
        return ban096;
    }

    public void setBan096(boolean ban096) {
        this.ban096 = ban096;
    }

    public boolean isBan106() {
        return ban106;
    }

    public void setBan106(boolean ban106) {
        this.ban106 = ban106;
    }

    public boolean isBan173() {
        return ban173;
    }

    public void setBan173(boolean ban173) {
        this.ban173 = ban173;
    }

    public boolean isBan939_53() {
        return ban939_53;
    }

    public void setBan939_53(boolean ban939_53) {
        this.ban939_53 = ban939_53;
    }

    public boolean isBan939_89() {
        return ban939_89;
    }

    public void setBan939_89(boolean ban939_89) {
        this.ban939_89 = ban939_89;
    }

    public int getSCP049amount() {
        return SCP049amount;
    }

    public void setSCP049amount(int SCP049amount) {
        this.SCP049amount = SCP049amount;
    }

    public int getSCP079amount() {
        return SCP079amount;
    }

    public void setSCP079amount(int SCP079amount) {
        this.SCP079amount = SCP079amount;
    }

    public int getSCP096amount() {
        return SCP096amount;
    }

    public void setSCP096amount(int SCP096amount) {
        this.SCP096amount = SCP096amount;
    }

    public int getSCP106amount() {
        return SCP106amount;
    }

    public void setSCP106amount(int SCP106amount) {
        this.SCP106amount = SCP106amount;
    }

    public int getSCP173amount() {
        return SCP173amount;
    }

    public void setSCP173amount(int SCP173amount) {
        this.SCP173amount = SCP173amount;
    }

    public int getSCP457amount() {
        return SCP457amount;
    }

    public void setSCP457amount(int SCP457amount) {
        this.SCP457amount = SCP457amount;
    }

    public int getSCP939_53amount() {
        return SCP939_53amount;
    }

    public void setSCP939_53amount(int SCP939_53amount) {
        this.SCP939_53amount = SCP939_53amount;
    }

    public int getSCP939_89amount() {
        return SCP939_89amount;
    }

    public void setSCP939_89amount(int SCP939_89amount) {
        this.SCP939_89amount = SCP939_89amount;
    }
}

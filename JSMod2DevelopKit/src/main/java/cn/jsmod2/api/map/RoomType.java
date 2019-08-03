/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.map;

public enum RoomType {

    UNDEFINED(0),
    WC00(1),
    SCP_914(2),
    AIRLOCK_00(3),
    AIRLOCK_01(4),
    CHECKPOINT_A(5),
    CHECKPOINT_B(6),
    HCZ_ARMORY(7),
    SERVER_ROOM(8),
    MICROHID(9),
    NUKE(10),
    SCP_012(11),
    SCP_049(12),
    SCP_079(13),
    SCP_096(14),
    SCP_106(15),
    SCP_173(16),
    SCP_372(17),
    SCP_939(18),
    ENTRANCE_CHECKPOINT(19),
    TESLA_GATE(20),
    PC_SMALL(21),
    PC_LARGE(22),
    GATE_A(23),
    GATE_B(24),
    CAFE(25),
    INTERCOM(26),
    DR_L(27),
    STRAIGHT(28),
    CURVE(29),
    T_INTERSECTION(30),
    X_INTERSECTION(31),
    LCZ_ARMORY(32),
    CLASS_D_CELLS(33),
    CUBICLES(34);

    private int type;

    RoomType(int type){
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

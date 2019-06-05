package cn.jsmod2.scpslserver.utils.api;

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

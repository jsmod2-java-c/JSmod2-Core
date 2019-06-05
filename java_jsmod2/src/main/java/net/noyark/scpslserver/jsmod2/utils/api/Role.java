package net.noyark.scpslserver.jsmod2.utils.api;


public enum Role {

    UNASSIGNED(-1),
    SCP_173(0),
    CLASSD(1),
    SPECTATOR(2),
    SCP_106(3),
    NTF_SCIENTIST(4),
    SCP_049(5),
    SCIENTIST(6),
    SCP_079(7),
    CHAOS_INSURGENCY(8),
    SCP_096(9),
    SCP_049_2(10),
    ZOMBIE(10),
    NTF_LIEUTENANT(11),
    NTF_COMMANDER(12),
    NTF_CADET(13),
    TUTORIAL(14),
    FACILITY_GUARD(15),
    SCP_939_53(16),
    SCP_939_89(17);

    private int role;

    Role(int role){
        this.role = role;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}

/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.team;


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

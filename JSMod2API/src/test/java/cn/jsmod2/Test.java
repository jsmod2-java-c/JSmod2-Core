package cn.jsmod2;

import cn.jsmod2.api.player.Player;
import cn.jsmod2.core.ApiId;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        System.out.println(new Player(""));
    }

}
enum Role {

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

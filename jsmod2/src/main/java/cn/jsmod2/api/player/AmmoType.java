/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.player;

public enum AmmoType {
    DROPPED_5(0), // Epsilon-11 Standard Rifle (Type 0)
    DROPPED_7(1), // MP7, Logicer (Type 1)
    DROPPED_9(2); // COM15, P90 (Type 2)

    private int ammo;

    public int getAmmo(){
        return ammo;
    }

    AmmoType(int ammo){
        this.ammo = ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }
}

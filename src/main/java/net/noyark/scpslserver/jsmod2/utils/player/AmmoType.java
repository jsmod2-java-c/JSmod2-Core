package net.noyark.scpslserver.jsmod2.utils.player;

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
}

package net.noyark.scpslserver.jsmod2.utils.player;

public enum WeaponType {

    COM15(0),
    P9(1),
    E11_STANDARD_RIFLE(2),
    MP7(3),
    LOGICER(4),
    USP(5);

    private int type;

    public int getType(){
        return type;
    }

    WeaponType(int type){
        this.type = type;
    }

}

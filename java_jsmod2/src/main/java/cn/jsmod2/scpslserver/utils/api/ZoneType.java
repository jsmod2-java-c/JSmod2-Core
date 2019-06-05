package cn.jsmod2.scpslserver.utils.api;

public enum ZoneType {

    UNDEFINED(0),
    LCZ(1),
    HCZ(2),
    ENTRANCE(3);

    private int type;

    ZoneType(int type){
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

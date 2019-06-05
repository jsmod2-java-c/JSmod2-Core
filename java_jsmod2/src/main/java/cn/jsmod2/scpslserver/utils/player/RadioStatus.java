package cn.jsmod2.scpslserver.utils.player;

public enum RadioStatus {

    CLOSE(0),
    SHORT_RANGE(1),
    MEDIUM_RANGE(2),
    LONG_RANGE(3),
    ULTRA_RANGE(4);

    private int status;

    public int getStatus(){
        return status;
    }

    RadioStatus(int status){
        this.status = status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

package net.noyark.scpslserver.jsmod2.utils.player;

public enum  UserRank {
    ADMIN(5),
    PROJECT_MANAGER(4),
    GAME_STAFF(3),
    BETATESTER(2),
    PATREON_SUPPORTED(1),
    NONE(0);

    private int rank;

    public int getRank(){
        return rank;
    }

    UserRank(int rank){
        this.rank = rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}

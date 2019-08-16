package cn.jsmod2.api.server;

public interface IRound {

    void endRound();

    void addNTFUnit(String unit);

    void MTFRespawn(boolean isCI);

    void restartRound();

    IRoundStats getStats();

    void setStats(IRoundStats stats);

    int getDuration();

    void setDuration(int duration);
}

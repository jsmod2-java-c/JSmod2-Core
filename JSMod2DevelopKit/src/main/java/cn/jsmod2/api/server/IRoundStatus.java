package cn.jsmod2.api.server;

public interface IRoundStatus {

    int getNTFAlive();

    int getScientistsAlive();

    int getScientlistsEscaped();

    int getScientistsDead();

    int getScientistsStart();

    int getClassDEscaped();

    int getClassDDead();

    int getClassDAlive();

    int getClassDStart();

    int getZombies();

    int getSCPDead();

    int getSCPKills();

    int getSCPAlive() ;

    int getSCPStart();

    int getGrenadeKills();

    boolean isWarheadDetonated();

    int getCiAlive();

    void setScientlistsEscaped(int scientlistsEscaped);

    void setClassDEscaped(int classDEscaped);
}

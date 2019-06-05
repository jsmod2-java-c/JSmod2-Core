package net.noyark.scpslserver.jsmod2.utils;

/**
 *
 * the type of log
 *
 * @author magiclu550
 *
 * @version jsmod2 001
 *
 */


public enum LogType {

    DEFAULT(0),

    DEBUG(1),

    INFO(2),

    WARN(3),

    ERROR(4);

    int level;
    LogType(Integer level){
        this.level = level;
    }

    public Integer getLevel(){
        return level;
    }
}

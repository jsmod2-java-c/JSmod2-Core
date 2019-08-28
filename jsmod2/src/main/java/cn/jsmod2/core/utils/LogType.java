/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core.utils;

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

    ERROR(4),

    CRIT(5);

    int level;
    LogType(Integer level){
        this.level = level;
    }

    public Integer getLevel(){
        return level;
    }
}

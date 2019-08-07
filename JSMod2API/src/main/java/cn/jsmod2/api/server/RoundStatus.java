/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.server;

import cn.jsmod2.core.ApiId;

import java.io.Serializable;

public class RoundStatus extends ApiId implements IRoundStatus, Serializable,Cloneable {

    private int NTFAlive;

    private int scientistsAlive;

    private int scientlistsEscaped;

    private int scientistsDead;

    private int scientistsStart;

    private int classDEscaped;

    private int classDDead;

    private int classDAlive;

    private int classDStart;

    private int zombies;

    private int SCPDead;

    private int SCPKills;

    private int SCPAlive;

    private int SCPStart;

    private int grenadeKills;

    private boolean warheadDetonated;

    private int ciAlive;

    public int getNTFAlive() {
        return NTFAlive;
    }

    public int getScientistsAlive() {
        return scientistsAlive;
    }

    public int getScientlistsEscaped() {
        return scientlistsEscaped;
    }

    public int getScientistsDead() {
        return scientistsDead;
    }

    public int getScientistsStart() {
        return scientistsStart;
    }

    public int getClassDEscaped() {
        return classDEscaped;
    }

    public int getClassDDead() {
        return classDDead;
    }

    public int getClassDAlive() {
        return classDAlive;
    }

    public int getClassDStart() {
        return classDStart;
    }

    public int getZombies() {
        return zombies;
    }

    public int getSCPDead() {
        return SCPDead;
    }

    public int getSCPKills() {
        return SCPKills;
    }

    public int getSCPAlive() {
        return SCPAlive;
    }

    public int getSCPStart() {
        return SCPStart;
    }

    public int getGrenadeKills() {
        return grenadeKills;
    }

    public boolean isWarheadDetonated() {
        return warheadDetonated;
    }

    public int getCiAlive() {
        return ciAlive;
    }

    public void setScientlistsEscaped(int scientlistsEscaped) {
        this.scientlistsEscaped = scientlistsEscaped;
    }

    public void setClassDEscaped(int classDEscaped) {
        this.classDEscaped = classDEscaped;
    }
}

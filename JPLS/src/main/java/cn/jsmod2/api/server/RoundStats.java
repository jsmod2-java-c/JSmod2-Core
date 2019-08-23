/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.server;

import cn.jsmod2.core.ApiId;
import cn.jsmod2.network.SimpleGetStream;
import cn.jsmod2.network.SimpleSetStream;

import java.io.Serializable;

public class RoundStats extends ApiId implements IRoundStats, Serializable,Cloneable {

    private int NTFAlive;

    private int scientistsAlive;

    private int scientistsEscaped;

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
        SimpleGetStream stream = new SimpleGetStream(Integer.class);
        NTFAlive = stream.read(playerName,"NTFAlive",Integer.class);
        return NTFAlive;
    }

    public int getScientistsAlive() {
        SimpleGetStream stream = new SimpleGetStream(Integer.class);
        scientistsAlive = scientistsAlive = stream.read(playerName,"ScientistsAlive",Integer.class);
        return scientistsAlive;
    }

    public int getScientistsEscaped() {
        SimpleGetStream stream = new SimpleGetStream(Integer.class);
        scientistsEscaped = stream.read(playerName,"ScientistsEscaped",Integer.class);
        return scientistsEscaped;
    }

    public int getScientistsDead() {
        SimpleGetStream stream = new SimpleGetStream(Integer.class);
        scientistsDead = stream.read(playerName,"ScientistsDead",Integer.class);
        return scientistsDead;
    }

    public int getScientistsStart() {
        SimpleGetStream stream = new SimpleGetStream(Integer.class);
        scientistsStart = stream.read(playerName,"ScientistsStart",Integer.class);
        return scientistsStart;
    }

    public int getClassDEscaped() {
        SimpleGetStream stream = new SimpleGetStream(Integer.class);
        classDEscaped = stream.read(playerName,"ClassDEscaped",Integer.class);
        return classDEscaped;
    }

    public int getClassDDead() {
        SimpleGetStream stream = new SimpleGetStream(Integer.class);
        classDDead = stream.read(playerName,"ClassDDead",Integer.class);
        return classDDead;
    }

    public int getClassDAlive() {
        SimpleGetStream stream = new SimpleGetStream(Integer.class);
        classDAlive = stream.read(playerName,"ClassDAlive",Integer.class);
        return classDAlive;
    }

    public int getClassDStart() {
        SimpleGetStream stream = new SimpleGetStream(Integer.class);
        classDStart = stream.read(playerName,"ClassDStart",Integer.class);
        return classDStart;
    }

    public int getZombies() {
        SimpleGetStream stream = new SimpleGetStream(Integer.class);
        zombies = stream.read(playerName,"Zombies",Integer.class);
        return zombies;
    }

    public int getSCPDead() {
        SimpleGetStream stream = new SimpleGetStream(Integer.class);
        SCPDead = stream.read(playerName,"SCPDead",Integer.class);
        return SCPDead;
    }

    public int getSCPKills() {
        SimpleGetStream stream = new SimpleGetStream(Integer.class);
        SCPKills = stream.read(playerName,"SCPKills",Integer.class);
        return SCPKills;
    }

    public int getSCPAlive() {
        SimpleGetStream stream = new SimpleGetStream(Integer.class);
        SCPAlive = stream.read(playerName,"SCPAlive",Integer.class);
        return SCPAlive;
    }

    public int getSCPStart() {
        SimpleGetStream stream = new SimpleGetStream(Integer.class);
        SCPStart = stream.read(playerName,"SCPStart",Integer.class);
        return SCPStart;
    }

    public int getGrenadeKills() {
        SimpleGetStream stream = new SimpleGetStream(Integer.class);
        grenadeKills = stream.read(playerName,"GrenadeKills",Integer.class);
        return grenadeKills;
    }

    public boolean isWarheadDetonated() {
        SimpleGetStream stream = new SimpleGetStream(Boolean.class);
        stream.read(playerName,"WarheadDetonated",Boolean.class);
        return warheadDetonated;
    }

    public int getCiAlive() {
        SimpleGetStream stream = new SimpleGetStream(Integer.class);
        ciAlive = stream.read(playerName,"CiAlive",Integer.class);
        return ciAlive;
    }

    public void setScientistsEscaped(int scientistsEscaped) {
        SimpleSetStream stream = new SimpleSetStream();
        stream.write(playerName,"ScientistsEscaped",scientistsEscaped);
        this.scientistsEscaped = scientistsEscaped;
    }

    public void setClassDEscaped(int classDEscaped) {
        SimpleSetStream stream = new SimpleSetStream();
        stream.write(playerName,"ClassDEscaped",classDEscaped);
        this.classDEscaped = classDEscaped;
    }
}

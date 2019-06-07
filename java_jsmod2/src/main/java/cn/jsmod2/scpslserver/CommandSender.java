/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.scpslserver;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Player,Console is command sender
 * @author magiclu550 #(code) jsmod2
 */

public abstract class CommandSender {

    private List<String> powers = new LinkedList<>();//powers

    private String name;

    protected CommandSender(String name,String... defaultPowers){
        this.name = name;
        this.powers.addAll(Arrays.asList(defaultPowers));
    }


    public String getName() {
        return name;
    }

    public List<String> getPowers(){
        return powers;
    }

    /** java bean */
    public void setPowers(List<String> powers) {
        this.powers = powers;
    }

    /** java bean */
    public void setName(String name) {
        this.name = name;
    }
}

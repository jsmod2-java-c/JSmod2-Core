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

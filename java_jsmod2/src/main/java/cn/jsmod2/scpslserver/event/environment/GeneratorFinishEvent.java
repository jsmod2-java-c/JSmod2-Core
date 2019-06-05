package cn.jsmod2.scpslserver.event.environment;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.utils.api.Generator;
import cn.jsmod2.scpslserver.event.Event;

/**
 * @author Kevinj
 * @author magiclu550
 */

public class GeneratorFinishEvent extends Event {
    private Generator generator;

    public GeneratorFinishEvent(Generator generator) {
        this.generator = generator;
    }

    public GeneratorFinishEvent(){

    }

    public Generator getGenerator() {
        return generator;
    }


    /** java-bean */
    @UseForServerInit
    public void setGenerator(Generator generator) {
        this.generator = generator;
    }
}

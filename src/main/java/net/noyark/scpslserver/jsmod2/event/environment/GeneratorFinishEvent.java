package net.noyark.scpslserver.jsmod2.event.environment;

import net.noyark.scpslserver.jsmod2.event.Event;
import net.noyark.scpslserver.jsmod2.utils.api.Generator;
import sun.nio.cs.Surrogate;

/**
 * @author Kevinj
 * @author magiclu550
 */

public class GeneratorFinishEvent extends Event {
    private Generator generator;

    public GeneratorFinishEvent(Generator generator) {
        this.generator = generator;
    }

    public Generator getGenerator() {
        return generator;
    }
}

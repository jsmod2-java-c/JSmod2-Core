package net.noyark.scpslserver.jsmod2.event.environment;

import net.noyark.scpslserver.jsmod2.event.Event;
import net.noyark.scpslserver.jsmod2.utils.api.Generator;
import sun.nio.cs.Surrogate;

/**
 * @author Kevinj
 */

public class GeneratorFinishEvent extends Event {
    public Generator generator;

    public GeneratorFinishEvent(Generator generator) {
        this.generator = generator;
    }
}

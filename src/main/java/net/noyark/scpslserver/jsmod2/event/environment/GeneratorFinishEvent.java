package net.noyark.scpslserver.jsmod2.event.environment;

import net.noyark.scpslserver.jsmod2.event.Event;
import sun.nio.cs.Surrogate;

public class GeneratorFinishEvent extends Event {
    public Generator generator;

    public GeneratorFinishEvent(Generator generator) {
        this.generator = generator;
    }
}

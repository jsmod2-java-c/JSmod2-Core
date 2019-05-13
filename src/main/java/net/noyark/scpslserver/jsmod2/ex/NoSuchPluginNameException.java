package net.noyark.scpslserver.jsmod2.ex;

public class NoSuchPluginNameException extends RuntimeException{

    public NoSuchPluginNameException(String name) {
        super("no such plugin named "+name);
    }
}

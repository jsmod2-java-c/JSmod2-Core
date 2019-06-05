package cn.jsmod2.scpslserver.ex;

public class NoSuchPluginNameException extends RuntimeException{

    public NoSuchPluginNameException(String name) {
        super("no such plugin named "+name);
    }
}

package net.noyark.scpslserver.jsmod2;

public class CommandConsoleSender {

    private Server commandConsoleServer;

    public CommandConsoleSender(Server server){
        this.commandConsoleServer = server;
    }

    public Server getServer(){
        return commandConsoleServer;
    }
}

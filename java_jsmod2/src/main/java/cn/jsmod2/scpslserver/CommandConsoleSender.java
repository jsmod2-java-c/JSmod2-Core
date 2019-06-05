package cn.jsmod2.scpslserver;

public class CommandConsoleSender {

    private Server commandConsoleServer;

    public CommandConsoleSender(Server server){
        this.commandConsoleServer = server;
    }

    public Server getServer(){
        return commandConsoleServer;
    }
}

package net.noyark.scpslserver.jsmod2;

import net.noyark.scpslserver.jsmod2.impl.log.ServerLogger;
import net.noyark.scpslserver.jsmod2.inferf.log.ILogger;

public class Jsmod2 {

    private static ILogger log = new ServerLogger();

    private static Server server;

    public static void main(String[]args){
        log.info("Server is starting........");
        log.info("Thank you for using this server: jsmod2 ");
        try{
            server = new Server();
        }catch (Exception e){
            log.error("ERROR!There is a Exception!!!!");
            e.printStackTrace();
        }
    }
}

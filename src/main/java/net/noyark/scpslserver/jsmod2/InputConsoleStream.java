package net.noyark.scpslserver.jsmod2;

public class InputConsoleStream {

    public static void commandInput(){
        while (true){
            System.out.print(">");
            String command = Server.getScanner().nextLine();
            if(command.equals("stop")){
                Server.getSender().getServer().close();
            }
        }
    }

}

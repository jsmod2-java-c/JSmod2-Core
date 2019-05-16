package net.noyark.scpslserver.jsmod2;

public class PacketManager {

    private static PacketManager manager;

    static {
        manager = new PacketManager();
    }


    private PacketManager(){
        
    }

    /**
     * 处理包的逻辑写在这里
     */
    public void manageMethod(String message,int id){

    }

    public static PacketManager getManager() {
        return manager;
    }
}

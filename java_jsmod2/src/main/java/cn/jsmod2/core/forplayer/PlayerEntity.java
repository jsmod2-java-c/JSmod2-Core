package cn.jsmod2.core.forplayer;

import cn.jsmod2.core.CommandSender;
import cn.jsmod2.core.ISimplePlayer;
import cn.jsmod2.core.math.Vector;

import java.util.List;
import java.util.Map;

public class PlayerEntity extends CommandSender implements ISimplePlayer,Move {

    private String ipAddress;

    private int id;

    private int port;

    private int health;

    private Map<String,String> entries;

    private Vector vector;


    public PlayerEntity(int id,String name,int port,int health,String ipAddress,Map<String,String> entries,Vector vector,String... defaultPowers) {
        super(name, defaultPowers);
        this.id = id;
        this.port = port;
        this.health = health;
        this.entries = entries;
        this.ipAddress = ipAddress;
        this.vector = vector;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    public int getPlayerId() {
        return id;
    }

    @Override
    public void kill() {

    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void addHealth(int amount) {

    }

    @Override
    public void personalBroadcast(int duration, String message, boolean isMonoSpaced) {

    }


    @Override
    public void move(Vector vector) {

    }

    public void send(String message,List<PlayerEntity> players){

    }

    public void accept(String message){

    }
}

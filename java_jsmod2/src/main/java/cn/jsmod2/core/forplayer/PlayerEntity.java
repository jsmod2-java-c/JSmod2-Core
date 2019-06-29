package cn.jsmod2.core.forplayer;

import cn.jsmod2.core.CommandSender;
import cn.jsmod2.core.ISimplePlayer;

import cn.jsmod2.core.math.Vector;
import com.alibaba.fastjson.JSON;


import java.io.IOException;
import java.util.Map;


public class PlayerEntity extends CommandSender implements ISimplePlayer,Move {

    private String ipAddress;

    private int id;

    private int port;

    private int health;

    private Map<String,String> entries;


    private PlayerServer server;



    public PlayerEntity(int id,String name,int port,int health,String ipAddress,Map<String,String> entries,Vector vector,PlayerServer server,PlayerPacketManager manager,String... defaultPowers) {
        super(name, defaultPowers);
        this.id = id;
        this.port = port;
        this.health = health;
        this.entries = entries;
        this.ipAddress = ipAddress;
        this.server = server;
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
        health += amount;
        send(new JsonRequester().add("type","add.health").add("name",getName()).add("health",amount+"").parse());
    }

    public void subHealth(int amount){
        health -= amount;
        send(new JsonRequester().add("type","sub.health").add("name",getName()).add("health",amount+"").parse());
    }

    @Override
    public void personalBroadcast(int duration, String message, boolean isMonoSpaced) {

    }


    @Override
    public void move(Vector vector) {
        send(new JsonRequester().add("type","move").add("x",vector.x()+"").add("y",vector.y()+"").add("z",vector.z()+"").parse());
    }
    @SuppressWarnings("unchecked")
    public void send(String message){
        Map<String,String> map = JSON.parseObject(message,Map.class);


        for(Map.Entry<String,String> entry:map.entrySet()){
            String name = entry.getKey();
            if(name.matches("name|type")){
                entries.put(entry.getKey(),entry.getValue());
            }
        }

        for(PlayerEntity entity:server.players){
            entity.accept(message);
        }
    }
    //根据type分发到各个方法

    public void accept(String message){
        try {
            server.sendData(message.getBytes(),ipAddress,port,false);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

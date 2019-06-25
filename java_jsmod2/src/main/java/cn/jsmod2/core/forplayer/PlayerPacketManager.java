package cn.jsmod2.core.forplayer;


import cn.jsmod2.core.Manager;
import cn.jsmod2.core.math.Vector;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static cn.jsmod2.core.forplayer.PlayerServer.DECODE_BY;


public class PlayerPacketManager implements Manager {

    private Map<String,Coder> coders = new HashMap<>();

    private Map<String,String> onlines = new HashMap<>();

    private PlayerServer server;

    private Properties properties;


    public PlayerPacketManager(PlayerServer server, Properties properties) {
        this.server = server;
        this.properties = properties;
        coders.put("base64",new Base64Coder());
    }

    /**
     * message属于将byte数组转化后的字符串，message默认是不开启base64加密
     * 管理玩家相关的信息
     * 本框架采用事件来更新玩家对象
     * {
     *      type create
     *      name
     *      ip
     *      port
     *      health
     *      x
     *      y
     *      z
     *      powers "p1:p2:p3"
     *      ...
     * }
     * {
     *     type set
     *     xx : xx
     * }
     * {
     *     type del
     *     name : xx
     *
     * }
     * @param message
     * @param id
     */
    // 玩家初始化 以json初始化玩家

    @Override
    @SuppressWarnings("unchecked")
    public void manageMethod(String message, int id) {
        message = decode(message);
        Map<String,String> playerStrings = JSON.parseObject(message,Map.class);
        String name = playerStrings.get("name");
        String type = playerStrings.get("type");
        if(onlines.containsKey(name)&&"create".equals(type)){
            int port = toInt(playerStrings.get("port"));
            int ID = toInt(playerStrings.get("id"));
            int health = toInt(playerStrings.get("health"));
            String ip = playerStrings.get("ip");
            int x = toInt(playerStrings.get("x"));
            int y = toInt(playerStrings.get("y"));
            int z = toInt(playerStrings.get("z"));
            Vector vector = new Vector(x,y,z);
            String[] powers = playerStrings.get("powers").split(":");
            PlayerEntity entity = new PlayerEntity(ID,name,port,health,ip,playerStrings,vector,powers);
            server.players.add(entity);
        }
    }

    private int toInt(String number){
        return Integer.parseInt(number);
    }


    public String decode(String message){
        String method = properties.getProperty(DECODE_BY,"base64");
        String[] methods = method.split(",");
        String after = message;
        for(String m:methods) {
            Coder coder = coders.get(m);
            if(coder!=null){
                after = coder.decode(after);
            }
        }
        return after;
    }
}

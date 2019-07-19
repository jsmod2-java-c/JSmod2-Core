package cn.jsmod2.core.forplayer;


import cn.jsmod2.core.Manager;
import cn.jsmod2.core.annotations.Param;
import cn.jsmod2.core.annotations.Type;
import cn.jsmod2.core.math.Vector;
import com.alibaba.fastjson.JSON;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

import static cn.jsmod2.core.forplayer.PlayerServer.DECODE_BY;


public class PlayerPacketManager extends Manager {

    private Map<String,Coder> coders = new HashMap<>();

    private Map<String,String> onLines = new HashMap<>();

    private PlayerServer server;

    private Properties properties;

    private RegisterType type;


    public PlayerPacketManager(PlayerServer server, Properties properties,RegisterType type) {
        this.server = server;
        this.properties = properties;
        coders.put("base64",new Base64Coder());
        this.type = type;
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
     *
     * {
     *     type del
     *     name : xx
     *
     * }
     * type: create del move 其余可以自定义
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
        if(onLines.containsKey(name)&&"create".equals(type)){
            int port = toInt(playerStrings.get("port"));
            int ID = toInt(playerStrings.get("id"));
            int health = toInt(playerStrings.get("health"));
            String ip = playerStrings.get("ip");
            int x = toInt(playerStrings.get("x"));
            int y = toInt(playerStrings.get("y"));
            int z = toInt(playerStrings.get("z"));
            Vector vector = new Vector(x,y,z);
            String[] powers = playerStrings.get("powers").split(":");
            PlayerEntity entity = new PlayerEntity(ID,name,port,health,ip,playerStrings,vector,server,this,powers);
            server.players.add(entity);
            onLines.put(name,"on");
            entity.send(message);
        }else if("del".equals(type)){
            PlayerEntity entity = getPlayer(name);
            if(entity!=null){
                entity.send(message);
                server.players.remove(entity);
                onLines.remove(name);
            }
        }else{
            PlayerEntity entity = getPlayer(name);
            Method method = getMethodByType(type);
            //根据参数的类型强转
            Parameter[] parameters = method.getParameters();
            List<Object> params = new ArrayList<>();//存储值
            params.add(entity);//本对象
            for(Parameter param:parameters){
                Param paramA = param.getAnnotation(Param.class);
                Class<?> param_type = param.getType();
                String val = playerStrings.get(paramA.value());
                if(param_type.equals(Integer.class)){
                    params.add(Integer.parseInt(val));
                }else if(param_type.equals(Long.class)){
                    params.add(Long.parseLong(val));
                }else if(param_type.equals(Boolean.class)){
                    params.add(Boolean.parseBoolean(val));
                }else if(param_type.equals(Double.class)){
                    params.add(Double.parseDouble(val));
                }else{
                    params.add(val);
                }
            }

            //指向方法
            try {
                method.invoke(getType(), params.toArray());
            }catch (IllegalAccessException| InvocationTargetException e){
                e.printStackTrace();
            }
        }
    }

    public Method getMethodByType(String type){
        Method[] methods = this.type.getClass().getDeclaredMethods();
        for(Method method:methods){
            Type tp = method.getAnnotation(Type.class);
            if(tp !=null){
                if(type.equals(tp.value())){
                    return method;
                }
            }
        }
        return null;
    }

    private PlayerEntity getPlayer(String name){
        for(PlayerEntity entity:server.players){
            if(entity.getName().equals(name)){
                return entity;
            }
        }
        return null;
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

    public RegisterType getType() {
        return type;
    }
}

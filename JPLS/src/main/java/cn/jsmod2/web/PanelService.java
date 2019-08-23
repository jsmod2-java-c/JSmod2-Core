package cn.jsmod2.web;

import cn.jsmod2.MethodInvoker;
import cn.jsmod2.core.ApiId;
import cn.jsmod2.core.RegisterTemplate;
import cn.jsmod2.core.Server;
import cn.jsmod2.core.ex.ServerRuntimeException;
import cn.jsmod2.core.protocol.BinaryStream;
import cn.jsmod2.core.protocol.DataPacket;
import cn.jsmod2.core.protocol.GetPacket;
import cn.jsmod2.core.protocol.Response;
import cn.jsmod2.core.utils.Utils;
import cn.jsmod2.ex.ApiErrorException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.*;

@Service
public class PanelService {

    //比如获取服务器最大人数
    //0x64-{"id":0x64,"type":"cn.jsmod2.server","field":"maxPlayers"}
    public Object api(String json) {
        int id = Utils.getResponsePacketId(new String(Base64.getEncoder().encode(json.getBytes())));
        Response response = new Response();
        response.future = Server.getSender().getServer().sendPacketGetResult(new DataPacket(id) {

            @Override
            public byte[] encode() {
                try {
                    return dataJsonEncode(json);
                } catch (Exception e) {
                    throw new ServerRuntimeException(e);
                }
            }
        });

        Map<Integer, Class<? extends DataPacket>> map = new HashMap<>();

        for (RegisterTemplate template : Server.getSender().getServer().getRegisters()) {
            map.putAll(template.getGetPackets());
        }
        try {
            DataPacket dataPacket = map.get(id).newInstance();
            if (dataPacket instanceof GetPacket) {
                response.packet = ((GetPacket) dataPacket);
            }
            Map<String, Object> things = new HashMap<>();
            things.put("json", response.get());
            return things;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //通过Server和Map调用的api
    //Server.Map 获取Map
    //Server.Port 获取Port
    //Server.Map.SpawnPoints(Role.xx){"field":"value"}通过指定字段的指定值找到一个对象
    //Server.Map->detonateWarhead
    // "Server":{
    //      "get":"Port"
    // }
    // "Server":{
    //      "get":"Round"
    //      "Round":{
    //          "get":"RadioStats"
    //          "RadioStats":{
    //              "set":"ScientistsEscaped"
    //              "values":[1]
    //          }
    //      }
    //  }
    // "Server":{
    //      "get":"Map"
    //      "Map":{
    //          "getList":"SpawnPoints"
    //          "values" : ["UNASSIGNED"]
    //          "index": 0
    //          "SpawnPoints":{
    //              "get":"x"
    //          }
    //      }
    //  }
    //
    //
    //
    //
    //
    public Object multiApi(String message) throws Exception{
        JSONObject object = JSON.parseObject(message);
        String str = (String) object.get("root");
        Object obj = object.get(str);//json对象
        Object root;
        if("Server".equals(str)){
            root = Server.getSender().getServer().getGameServer();
        }else{
            root = Server.getSender().getServer().getGameServer().getMap();
        }//查找对象
        return parse(obj,root);
    }

    //obj 此时json对象 root 此时获取的对象
    private Object parse(Object obj,Object root) throws Exception{
        if(obj instanceof JSONObject){
            JSONObject json;
            while (true){
                json = (JSONObject)obj;
                if(json.containsKey("get")){
                    String get = (String) json.get("get");
                    String getName = get;//获得名字
                    get = "get"+get;//拼接为get方法
                    JSONArray values = (JSONArray) json.get("values");//参数
                    //获取values，参数
                    MethodInvoker invoker = invokeMethod(values,root,get);
                    root = invoker.getMethod().invoke(root,invoker.getObjVals().toArray());//root替换为新root对象
                    obj = json.get(getName);
                    if(obj == null){
                        return root;//如果obj==null，说明到了尽头，那么返回root
                    }

                }else if(json.containsKey("set")){
                    //set一定没有返回值，因此set只执行一次，返回null
                    String set = json.get("set").toString();
                    set = "set"+set;
                    MethodInvoker invoker = invokeMethod((JSONArray) json.get("values"),root,set);
                    invoker.getMethod().invoke(root,invoker.getObjVals().toArray());
                    return null;
                }else if(json.containsKey("getList")){
                    String list = json.get("getList").toString();
                    String listName = list;
                    list = "get"+list;
                    MethodInvoker invoker = invokeMethod((JSONArray) json.get("values"),root,list);
                    root = invoker.getMethod().invoke(root,invoker.getObjVals());
                    if(json.containsKey("index")){
                        //下标获取
                        root = ((JSONArray)root).get((Integer) json.get("index"));
                    }
                    obj = json.get(listName);
                    if(obj == null){
                        return root;//如果obj==null，说明到了尽头，那么返回root
                    }
                }else if(json.containsKey("do")){
                   String doName = json.get("do").toString();
                   MethodInvoker invoker = invokeMethod(json.getJSONArray("values"),root,doName);
                   root = invoker.getMethod().invoke(obj,invoker.getObjVals());
                   obj = json.get(doName);
                   if(obj == null){
                       return root;
                   }
                }
            }
        }else {
            throw new ApiErrorException("at least one response value (The basic value) except 'get'");
        }
    }

    private MethodInvoker invokeMethod(JSONArray values, Object root, String get) throws Exception{
        List<Object> objVals = new ArrayList<>();
        Method method = null;
        if(values==null){//如果没有参数，则默认午餐
            method = root.getClass().getDeclaredMethod(get);
        }else{//如果有参数
            List<Method> methods = getMethod(get,root.getClass().getMethods());
            for(Method method1:methods){
                if(method1.getParameterTypes().length == values.size()){
                    method = method1;
                    break;
                }
            }
            if(method == null){
                throw new ApiErrorException("no such get or set or do method");
            }

            Class<?>[] paramTypes = method.getParameterTypes();
            int i = 0;
            for(Object value : values){
                Object find = ((JSONObject)value).get(i+"");
                if(find instanceof JSONObject){
                    if(((JSONObject) find).containsKey("x")){
                        objVals.add(JSON.parseObject(((JSONObject) find).toJSONString(),paramTypes[i]));
                    }else{
                        //当前对象 root对象 json find
                        String str = ((JSONObject) find).get("root").toString();
                        Object pRoot;
                        if("Server".equals(str)){
                            pRoot = Server.getSender().getServer().getGameServer();
                        }else{
                            pRoot = Server.getSender().getServer().getGameServer().getMap();
                        }//查找对象
                        Object returnValue = parse(((JSONObject) find).get(str), pRoot);//解析数组
                        objVals.add(returnValue);
                    }

                }else{
                    if(find instanceof JSONArray){
                        objVals.add(JSON.parseArray(((JSONArray) find).toJSONString(),paramTypes[i]));
                    }else{
                        objVals.add(JSON.parseObject(find.toString(),paramTypes[i]));
                    }
                }
                i++;
            }
            //json对象也替换为下一代
            //要判断类型
        }
        return new MethodInvoker(objVals,method);
    }

    private List<Method> getMethod(String name, Method[] methods){
        List<Method> methodList = new ArrayList<>();
        for(Method method:methods){
            if(method.getName().equals(name)){
                methodList.add(method);
            }
        }
        return methodList;
    }

}

package cn.jsmod2.core.forplayer;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class JsonRequester {

    private Map<String,String > alls = new HashMap<>();


    public JsonRequester add(String key,String value){
        alls.put(key, value);
        return this;
    }

    public String parse(){
        return JSON.toJSON(alls).toString();
    }

}

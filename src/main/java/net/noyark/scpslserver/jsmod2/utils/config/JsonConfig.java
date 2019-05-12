package net.noyark.scpslserver.jsmod2.utils.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.noyark.oaml.DocumentFactory;
import net.noyark.oaml.exception.AmbiguousTypeException;
import net.noyark.oaml.tree.Document;
import net.noyark.scpslserver.jsmod2.utils.Utils;

import java.io.*;
import java.util.*;

public class JsonConfig implements Config {

    private String fileName;

    private JSONObject jsonObject;
    //读取
    private InputStream inputStream;
    //写入
    private OutputStream outputStream;

    private JSONObject nowObject;

    private PrintWriter writer;

    private Map<String,JSONObject> exist = new HashMap<>();

    public JsonConfig(String fileName,boolean getClass) throws IOException{
       if(getClass){
           this.fileName = Utils.getClassFileName(fileName);
       }else{
           this.fileName = fileName;
       }
       this.jsonObject = new JSONObject();
    }

    @Override
    public void put(String key, Object value) throws FileNotFoundException {
        String[] keys = key.split("\\.");
        JSONObject object = jsonObject;
        StringBuilder builder = new StringBuilder(keys[0]);
        JSONObject obj = exist.get(builder.toString());
        if(obj != null){
            object = obj;
        }else{
            obj = new JSONObject();
            jsonObject.put(builder.toString(),obj);
            object = obj;
            for(int i =1;i<keys.length-1;i++){
                builder.append("."+keys[i]);
                obj = exist.get(builder.toString());
                JSONObject jsonObject;
                if(obj != null){
                    jsonObject = obj;
                }else{
                    jsonObject = new JSONObject();
                    exist.put(builder.toString(),object);
                    object.put(keys[i],jsonObject);
                }
                object = jsonObject;
            }
        }
        if(value instanceof Collection){
            value = JSONArray.parseArray(value.toString());
        }
        object.put(keys[keys.length-1],value);
        this.nowObject = jsonObject;
    }


    @Override
    public Object get(String key) throws IOException {
        inputStream = new FileInputStream(fileName);
        BufferedReader reader = Utils.getReader(inputStream);
        StringBuilder builder = new StringBuilder();
        String line;
        while((line = reader.readLine())!=null){
            builder.append(line);
        }
        JSONObject obj = JSON.parseObject(builder.toString());
        String[] keys = key.split("\\.");
        for(String one:keys){
            Object o = obj.get(one);
            if(o instanceof JSONObject){
                obj = ((JSONObject)o);
            }else{
                return obj.get(one);
            }
        }
        return obj.get(keys[keys.length-1]);
    }

    @Override
    public void save() throws UnsupportedEncodingException, FileNotFoundException {
        if(outputStream == null){
            outputStream = new FileOutputStream(fileName);
        }
        writer = new PrintWriter(outputStream,true);
        writer.println(Utils.format(nowObject.toJSONString()));
    }

    @Override
    public void putAll(LinkedHashMap<String, Object> map) throws FileNotFoundException {
        jsonObject.putAll(map);
    }

    @Override
    public String[] getArray(String key) throws IOException {
        String str = get(key).toString();
        if(!(str.startsWith("[")&&str.endsWith("]"))){
            throw new AmbiguousTypeException("this type is not array!");
        }else{
            str = str.substring(str.indexOf("[")+1,str.indexOf("]"));
        }
        return str.split(",");
    }

    @Override
    public List<?> getList(String key) throws IOException {
        return null;
    }

    @Override
    public String getArrayValue(String key, int index) throws IOException {
        return null;
    }


    @Override
    public void remove(String key) throws IOException {

    }

    @Override
    public Object getObject(String key, Class<?> type) throws InstantiationException, IllegalAccessException, IOException {
        return null;
    }

    @Override
    public void setObject(String key, Object obj) throws IOException {

    }

    @Override
    public void close() {
        writer.close();
    }

    @Override
    public void setEncoding(String cdn) {

    }

    @Override
    public String getEncoding() {
        return null;
    }

    @Override
    public Document getDocument() {
        return null;
    }

    @Override
    public Map<String, Object> getAll(String parentPath) throws IOException {
        return null;
    }

    @Override
    public void setNote(String key, String note) throws IOException {

    }

    @Override
    public Object[] getObjectArray(String key, Class<?> defaultType, Class<?>... type) throws IllegalArgumentException, IllegalAccessException, InstantiationException, IOException {
        return new Object[0];
    }

    @Override
    public List<Object> getObjectList(String key, Class<?> defaultType, Class<?>... type) throws IllegalArgumentException, IllegalAccessException, InstantiationException, IOException {
        return null;
    }
}

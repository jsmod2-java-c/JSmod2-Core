/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core.utils.config;

import cn.jsmod2.core.utils.Utils;

import java.io.*;
import java.util.*;

public class PropertiesConfig extends Config{

    private Properties properties;



    public PropertiesConfig(String fileName,boolean getClass) {
        super(fileName, getClass);
        try{
            properties = new Properties();
            if(getClass){
                out = new FileOutputStream(Utils.getClassFileName(fileName));
            }else{
                out = new FileOutputStream(fileName);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void put(String key, Object value) throws IOException {
        properties.setProperty(key,value+"");
        properties.store(out,"");
    }

    @Override
    public Object get(String key) throws IOException {
        return get(key,new FileInputStream(fileName));
    }

    @Override
    public Object get(String key,InputStream in) throws IOException{
        this.in = in;
        properties.load(in);
        return properties.get(key);
    }

    @Override
    public void save() throws IOException {
        out.flush();
    }

    @Override
    public void putAll(LinkedHashMap<String, Object> map) {
        properties.putAll(map);
    }

    @Override
    public String[] getArray(String key) throws IOException {
        String str = get(key).toString();
        if(str.contains("[")&&str.contains("]")){
            str = str.substring(str.indexOf("[")+1,str.indexOf("]"));
            return str.split(",");
        }
       return null;
    }

    @Override
    public List<?> getList(String key) throws IOException {
        return Arrays.asList(getArray(key));
    }


    @Override
    public String getArrayValue(String key, int index) throws IOException {
        return getArray(key)[index];
    }


    @Override
    public void remove(String key) {
        properties.remove(key);
    }

    @Override
    public Object getObject(String key, Class<?> type) throws IOException {
        return get(key);
    }

    @Override
    public void setObject(String key, Object obj) throws IOException {
        put(key,obj);
    }

    @Override
    public void close() {
        try{
            out.close();
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void setEncoding(String cdn) {}

    @Override
    public String getEncoding() {
        return "default";
    }

    @Override
    public Properties getDocument() {
        return properties;
    }

    @Override
    public Map<String, Object> getAll(String parentPath) {
        Set<Map.Entry<Object,Object>> set = properties.entrySet();
        Map<String,Object> map = new HashMap<>();
        for(Map.Entry<Object,Object> entry:set){
            map.put(entry.getKey().toString(),entry.getValue());
        }
        return map;
    }

    @Override
    public void setNote(String key, String note) throws IOException {
        properties.store(out,note);
    }

    @Override
    public Object[] getObjectArray(String key, Class<?> defaultType, Class<?>... type) throws IllegalArgumentException, IOException {
        return new Object[]{getObject(key,defaultType)};
    }

    @Override
    public List<Object> getObjectList(String key, Class<?> defaultType, Class<?>... type) throws IllegalArgumentException, IOException {
        return Arrays.asList(getObjectArray(key,defaultType));
    }
}

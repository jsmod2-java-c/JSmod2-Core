package net.noyark.scpslserver.jsmod2.utils.config;

import net.noyark.scpslserver.jsmod2.utils.Utils;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.*;

/**
 * @author magiclu550 #(code) jsmod2
 */

public class YamlConfig extends Config {

    private Yaml yaml;

    private PrintWriter writer;

    private Map<String,Object> rootMap;

    private Map<String,Map<String,Object>> exist;

    public YamlConfig(String fileName,boolean getClass){
        super(fileName,getClass);
        this.yaml = new Yaml();
        this.rootMap = new LinkedHashMap<>();
        exist = new HashMap<>();
    }

    @Override
    public void put(String key, Object value) throws IOException {
        String[] keys = key.split("\\.");
        Map<String,Object> object = rootMap;
        StringBuilder builder = new StringBuilder(keys[0]);
        Map<String,Object> obj = exist.get(builder.toString());
        if(obj != null){
            object = obj;
        }else{
            obj = new LinkedHashMap<>();
            rootMap.put(builder.toString(),obj);
            object = obj;
            for(int i =1;i<keys.length-1;i++){
                builder.append("."+keys[i]);
                obj = exist.get(builder.toString());
                Map<String,Object> mapObject;
                if(obj != null){
                    mapObject = obj;
                }else{
                    mapObject = new LinkedHashMap<>();
                    exist.put(builder.toString(),object);
                    object.put(keys[i],mapObject);
                }
                object = mapObject;
            }
        }
        object.put(keys[keys.length-1],value);
    }

    @Override
    public Object get(String key) throws IOException {
        String[] keys = key.split("\\.");
        return get(keys).get(keys[keys.length-1]);
    }

    @Override
    public Object get(String key,InputStream in) throws IOException{
        if(key.equals(".")){
            return yaml.load(in);
        }
        String[] keys = key.split("\\.");
        return get(keys,in).get(keys[keys.length-1]);
    }

    @Override
    public void save() throws IOException {
        if(out == null){
            out = new FileOutputStream(fileName);
            writer = Utils.getWriter(out);
        }
        yaml.dump(rootMap,writer);
    }

    @Override
    public void putAll(LinkedHashMap<String, Object> map) throws FileNotFoundException {
        rootMap.putAll(map);
    }

    @Override
    public String[] getArray(String key) throws IOException {
        Object obj = get(key);

        if(obj instanceof List){
            List object = ((List) obj);
            return (String[])(object.toArray(new String[object.size()]));
        }
        return new String[0];
    }

    @Override
    public List<?> getList(String key) throws IOException {
        return (List)get(key);
    }

    @Override
    public String getArrayValue(String key, int index) throws IOException {
        return getArray(key)[index];
    }

    @Override
    public void remove(String key) throws IOException {
        String[] keys = key.split("\\.");
        get(Utils.getParentArray(keys)).remove(keys[keys.length-1]);
    }

    @Override
    public Object getObject(String key, Class<?> type) throws InstantiationException, IllegalAccessException, IOException {
        return get(key);
    }

    @Override
    public void setObject(String key, Object obj) throws IOException {
        put(key, obj);
    }

    @Override
    public void close() {
        writer.close();
    }

    @Override
    public void setEncoding(String cdn) {}

    @Override
    public String getEncoding() {
        return "default";
    }

    @Override
    public Map getDocument() {
        return rootMap;
    }

    @Override
    public Map getAll(String parentPath) throws IOException {
        return get(Utils.getParentArray(parentPath.split("\\.")));
    }

    @Override
    public void setNote(String key, String note) throws IOException {}

    @Override
    public Object[] getObjectArray(String key, Class<?> defaultType, Class<?>... type) throws IllegalArgumentException, IllegalAccessException, InstantiationException, IOException {
        List list = ((List)get(key));
        return list.toArray(new Object[list.size()]);
    }

    @Override
    public List<Object> getObjectList(String key, Class<?> defaultType, Class<?>... type) throws IllegalArgumentException, IllegalAccessException, InstantiationException, IOException {
        return (List) get(key);
    }
    private Map get(String[] keys) throws IOException{
        return get(keys,new FileInputStream(fileName));
    }

    private Map get(String[] keys,InputStream in) throws IOException{
        this.in = in;
        Map map = yaml.loadAs(in,LinkedHashMap.class);
        for(String one:keys){
            Object obj = map.get(one);
            if(obj instanceof Map){
                map = (Map)obj;
            }
        }
        in.close();
        return map;
    }
}

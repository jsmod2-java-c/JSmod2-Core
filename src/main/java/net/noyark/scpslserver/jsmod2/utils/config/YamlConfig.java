package net.noyark.scpslserver.jsmod2.utils.config;


import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class YamlConfig implements Config {

    private Yaml yaml;

    public YamlConfig(String fileName,boolean getClass){
        this.yaml = new Yaml();

    }


    @Override
    public void put(String key, Object value) throws IOException {

    }

    @Override
    public Object get(String key) throws IOException {
        return null;
    }

    @Override
    public void save() throws IOException {

    }

    @Override
    public void putAll(LinkedHashMap<String, Object> map) throws FileNotFoundException {

    }

    @Override
    public String[] getArray(String key) throws IOException {
        return new String[0];
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

    }

    @Override
    public void setEncoding(String cdn) {

    }

    @Override
    public String getEncoding() {
        return null;
    }

    @Override
    public Object getDocument() {
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

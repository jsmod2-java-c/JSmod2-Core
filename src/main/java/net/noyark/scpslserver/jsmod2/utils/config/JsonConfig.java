package net.noyark.scpslserver.jsmod2.utils.config;

import net.noyark.oaml.tree.Document;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonConfig implements IConfig{

    @Override
    public void put(String key, Object value) throws FileNotFoundException {

    }

    @Override
    public Object get(String key) throws IOException {
        return null;
    }

    @Override
    public void save() throws UnsupportedEncodingException, FileNotFoundException {

    }

    @Override
    public void putAll(LinkedHashMap<String, Object> map) throws FileNotFoundException {

    }

    @Override
    public int getInt(String key) throws IOException {
        return 0;
    }

    @Override
    public byte getByte(String key) throws IOException {
        return 0;
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
    public short getShort(String key) throws IOException {
        return 0;
    }

    @Override
    public boolean getBoolean(String key) throws IOException {
        return false;
    }

    @Override
    public long getLong(String key) throws IOException {
        return 0;
    }

    @Override
    public double getDouble(String key) throws IOException {
        return 0;
    }

    @Override
    public float getFloat(String key) throws IOException {
        return 0;
    }

    @Override
    public char getChar(String key) throws IOException {
        return 0;
    }

    @Override
    public String getArrayValue(String key, int index) throws IOException {
        return null;
    }

    @Override
    public Date getDate(String key, String format) throws ParseException, IOException {
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

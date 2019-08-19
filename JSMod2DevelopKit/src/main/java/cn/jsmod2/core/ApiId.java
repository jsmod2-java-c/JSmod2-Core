package cn.jsmod2.core;

import cn.jsmod2.core.annotations.PlayerName;
import cn.jsmod2.core.utils.Utils;

import java.io.Serializable;
import java.lang.reflect.Field;

//这个用来定位实体api在smod2的对象位置
public abstract class ApiId implements Cloneable, Serializable {

    @PlayerName
    protected String playerName;

    @Override
    public String toString() {
        try{
            StringBuilder builder = new StringBuilder();
            Class<?> clz = getClass();
            builder.append(clz.getSimpleName());
            builder.append("{\n");
            for(Field field:clz.getDeclaredFields()){
                field.setAccessible(true);
                builder.append(field.getName());
                builder.append(":");
                builder.append(field.get(this));
                builder.append("\n");
            }
            builder.append("}");
            return builder.toString();
        }catch (IllegalAccessException e){
            Utils.printException(e);
        }
        return playerName == null?"SERVER":playerName;
    }

    public String getApiId() {
        return playerName;
    }
}

package cn.jsmod2.core;

import cn.jsmod2.core.annotations.PlayerName;

import java.io.Serializable;

//这个用来定位实体api在smod2的对象位置
public abstract class ApiId implements Cloneable, Serializable {

    @PlayerName
    protected String playerName;

    @Override
    public String toString() {
        return playerName;
    }

    public String getApiId() {
        return playerName;
    }
}

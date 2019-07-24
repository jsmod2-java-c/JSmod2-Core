package cn.jsmod2.core;

import cn.jsmod2.core.annotations.PlayerName;

//这个用来定位实体api在smod2的对象位置
public class ApiId {

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

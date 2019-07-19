package cn.jsmod2.core.utils;

/**
 * 该类可以返回发出请求后相应的值(base64加密)
 */
public class Result implements Future {

    private byte[] base64Value;

    public byte[] get(){
        return base64Value;
    }

    @Override
    public void set(byte[] bytes) {
        this.base64Value = bytes;
    }
}

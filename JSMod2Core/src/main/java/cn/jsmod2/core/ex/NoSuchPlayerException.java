package cn.jsmod2.core.ex;

/**
 * 当在搜寻玩家时，如果发现该玩家不存在，可以抛出该异常
 * @author magiclu550
 */

public class NoSuchPlayerException extends ServerRuntimeException{
    public NoSuchPlayerException() {
        super();
    }

    public NoSuchPlayerException(String message) {
        super(message);
    }
}

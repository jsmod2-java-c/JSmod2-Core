package cn.jsmod2.core.protocol.command;

import cn.jsmod2.core.ISimplePlayer;
import cn.jsmod2.core.annotations.UseForServerInit;

/**
 * 定义了指令数据的内容规范
 * @param <T>
 */
public abstract class AbstractPlayerVO<T extends ISimplePlayer> extends CommandVO {

    protected T player;


    public T getPlayer() {
        return player;
    }
    @UseForServerInit
    public void setPlayer(T player) {
        this.player = player;
    }


}

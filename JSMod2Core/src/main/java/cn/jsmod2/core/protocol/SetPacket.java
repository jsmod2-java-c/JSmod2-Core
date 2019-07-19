package cn.jsmod2.core.protocol;


/**
 * 做一个事情:do-方法全小写名
 * 修改值:字段名-值
 * 封装了设置数据包的操作
 */
public abstract class SetPacket extends ControlPacket {

    public SetPacket(int id) {
        super(id);
    }

    protected static final String DO = "do";

    public abstract void send();
}

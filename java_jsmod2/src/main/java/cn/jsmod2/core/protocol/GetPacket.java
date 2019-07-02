package cn.jsmod2.core.protocol;

/**
 * 可以获取返回的数据
 */

public abstract class GetPacket extends ControlPacket{

    private Class<?> type;

    public GetPacket(int id,Class<?> type) {
        super(id);
        this.type = type;
    }

    public Class<?> getType() {
        return type;
    }

    @Override
    public Object decode(byte[] bytes) {
        return dataObjectDecode(bytes,type);
    }

    public abstract Object send();
}

package cn.jsmod2.core.protocol;

/**
 * 可以获取返回的数据
 */

public abstract class GetPacket<T> extends ControlPacket{

    private Class<T> type;

    public GetPacket(int id,Class<T> type) {
        super(id);
        this.type = type;
    }

    public Class<T> getType() {
        return type;
    }

    @Override
    public Object decode(byte[] bytes) {
        return dataObjectDecode(bytes,type);
    }

    public abstract Object send();
}

package cn.jsmod2.network.protocol.map.elevator;

public class GetElevatorLockedPacket extends GetElevatorPacket {

    public static final int ID = 121;

    public GetElevatorLockedPacket() {
        super(ID,Boolean.class);
    }

    @Override
    public Boolean send() {
        return (Boolean) requester.with("field","locked").get().get();
    }
}

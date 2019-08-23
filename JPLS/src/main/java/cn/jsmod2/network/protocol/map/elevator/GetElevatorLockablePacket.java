package cn.jsmod2.network.protocol.map.elevator;


public class GetElevatorLockablePacket extends GetElevatorPacket {


    public static final int ID = 123;

    public GetElevatorLockablePacket() {
        super(ID, Boolean.class);
    }

    @Override
    public Boolean send() {
        return (Boolean)requester.with("field","lockable").get().get();
    }
}

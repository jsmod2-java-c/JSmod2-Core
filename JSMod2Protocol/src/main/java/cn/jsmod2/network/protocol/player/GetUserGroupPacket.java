package cn.jsmod2.network.protocol.player;

import cn.jsmod2.api.user.UserGroup;

public class GetUserGroupPacket extends GetPlayerPacket{

    public GetUserGroupPacket() {
        super(194, UserGroup.class);
    }

    @Override
    public UserGroup send() {
        return (UserGroup)requester.with("method","GetUserGroup").get().get();
    }
}

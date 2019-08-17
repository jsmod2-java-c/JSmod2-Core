package cn.jsmod2.network.protocol.event.admin;

import cn.jsmod2.api.item.ItemType;
import cn.jsmod2.core.utils.Utils;
import cn.jsmod2.network.protocol.event.packet.EventSetPacket;


import java.util.ArrayList;
import java.util.List;

public class PlayerSetRoleSetItemsPacket extends EventSetPacket {

    public static final int ID = 183;

    public List<ItemType> items;

    public PlayerSetRoleSetItemsPacket() {
        super(ID);
    }

    @Override
    public void send() {
        List<String> itemStrings = new ArrayList<>();
        for(ItemType type : items){
            itemStrings.add(type.getType()+"");
        }
        requester.with("items",Utils.arraysToString((String[]) itemStrings.toArray())).to();
    }
}

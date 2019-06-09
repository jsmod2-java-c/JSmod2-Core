/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.map;

import cn.jsmod2.annotations.FieldInsert;
import cn.jsmod2.api.item.Item;
import cn.jsmod2.api.item.ItemType;
import cn.jsmod2.api.team.Role;

import java.util.List;

public class Map {

    //全部物品
    @FieldInsert
    private List<Item> allItems;

    //对于条件获得物品，将全部物品穷举出来，之后进行逻辑判断
    //C#端完成
    public List<Item> getItems(ItemType type,boolean world_only){
        return null;
    }






}

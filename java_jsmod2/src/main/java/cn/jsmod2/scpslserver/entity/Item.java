package cn.jsmod2.scpslserver.entity;

import cn.jsmod2.scpslserver.utils.item.ItemType;

import java.io.Serializable;

public class Item implements Cloneable, Serializable {

    private boolean inWord;

    private ItemType itemType;

    public boolean isInWord() {
        return inWord;
    }

    public ItemType getItemType() {
        return itemType;
    }
}

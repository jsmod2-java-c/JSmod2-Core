package net.noyark.scpslserver.jsmod2.entity;

import net.noyark.scpslserver.jsmod2.utils.item.ItemType;

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

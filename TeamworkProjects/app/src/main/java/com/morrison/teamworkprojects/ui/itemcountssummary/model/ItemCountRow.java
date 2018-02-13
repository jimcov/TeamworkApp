package com.morrison.teamworkprojects.ui.itemcountssummary.model;

import com.morrison.teamworkprojects.model.ItemCount;

public class ItemCountRow<T> {
    private T item;
    private int count;
    private int color;

    public ItemCountRow(final ItemCount<T> itemCount, final int color) {
        this.item = itemCount.getItem();
        this.count = itemCount.getCount();
        this.color = color;
    }

    public T getItem() {
        return item;
    }

    public int getCount() {
        return count;
    }

    public int getColor() {
        return color;
    }

}

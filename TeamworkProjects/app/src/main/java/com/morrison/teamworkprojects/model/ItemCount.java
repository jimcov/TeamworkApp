package com.morrison.teamworkprojects.model;

public class ItemCount<T> {
    private T item;
    private int count;

    public ItemCount(final T item, final int count) {
        this.item = item;
        this.count = count;
    }

    public T getItem() {
        return item;
    }

    public int getCount() {
        return count;
    }

}

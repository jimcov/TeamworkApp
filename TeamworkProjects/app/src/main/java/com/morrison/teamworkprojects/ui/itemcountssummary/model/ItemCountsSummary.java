package com.morrison.teamworkprojects.ui.itemcountssummary.model;

import com.morrison.teamworkprojects.model.ColorProvider;
import com.morrison.teamworkprojects.model.ItemCount;

import java.util.ArrayList;
import java.util.List;

public class ItemCountsSummary<T> {
    private String title;
    private List<ItemCount<T>> itemCounts;
    private List<ItemCountRow> itemCountRows;
    private ColorProvider<T> colorProvider;
    private int totalItemsCount;

    public ItemCountsSummary(final String title, final List<ItemCount<T>> itemCounts, final ColorProvider<T> colorProvider) {
        this.title = title;
        this.itemCounts = itemCounts;
        this.colorProvider = colorProvider;

        initAttributes();
        initItemCountRows();
    }

    private void initAttributes() {
        totalItemsCount = 0;
        for(ItemCount<T> itemCount : itemCounts) {
            totalItemsCount += itemCount.getCount();
        }
    }

    private void initItemCountRows() {
        itemCountRows = new ArrayList<ItemCountRow>();
        for(ItemCount<T> itemCount : itemCounts) {
            itemCountRows.add(getItemCountRow(itemCount));
        }
    }

    private ItemCountRow getItemCountRow(final ItemCount<T> itemCount) {
        return new ItemCountRow(itemCount, colorProvider.getColor(itemCount.getItem()));
    }

    public String getTitle() {
        return title + " (" + totalItemsCount + ")";
    }

    public List<ItemCountRow> getItemCountRows() {
        return itemCountRows;
    }

}

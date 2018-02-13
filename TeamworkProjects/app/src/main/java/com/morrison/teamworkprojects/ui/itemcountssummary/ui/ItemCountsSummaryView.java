package com.morrison.teamworkprojects.ui.itemcountssummary.ui;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;

import com.morrison.teamworkprojects.model.TimeSlot;
import com.morrison.teamworkprojects.ui.itemcountssummary.model.ItemCountRow;
import com.morrison.teamworkprojects.ui.itemcountssummary.model.ItemCountsSummary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemCountsSummaryView extends RelativeLayout
{
    private ItemCountsSummary itemCountsSummary;
    private TextView titleView;
    private Map<TimeSlot, TextView> timeSlotViewMap = new HashMap<TimeSlot, TextView>();

    public ItemCountsSummaryView(final Context context) {
        super(context);
        invalidate();
    }

    public ItemCountsSummaryView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        invalidate();
        titleView = new TextView(context, attrs);
        addView(titleView);
        titleView.setPadding(0, 0, 0, 8);
        titleView.setId(View.generateViewId());
        titleView.setTextSize(16.0f);

        int previousID = titleView.getId();
        for(TimeSlot timeSlot : TimeSlot.values()) {
            previousID = addRowForTimeSlot(timeSlot, previousID, context, attrs);
        }
    }

    private int addRowForTimeSlot(final TimeSlot timeSlot, final int previousID, final Context context, final AttributeSet attrs) {
        TextView nextView = new TextView(context, attrs);
        nextView.setId(View.generateViewId());
        nextView.setText(timeSlot.getName());
        RelativeLayout.LayoutParams nameViewParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        nameViewParams.addRule(RelativeLayout.BELOW, previousID);
        addView(nextView, nameViewParams);

        TextView countView = new TextView(context, attrs);
        timeSlotViewMap.put(timeSlot, countView);
        RelativeLayout.LayoutParams countViewParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        countViewParams.addRule(RelativeLayout.BELOW, previousID);
        countViewParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        addView(countView, countViewParams);

        return nextView.getId();
    }

    public void setItemCountsSummary(final ItemCountsSummary itemCountsSummary) {
        this.itemCountsSummary = itemCountsSummary;
        titleView.setText(itemCountsSummary.getTitle());

        List<ItemCountRow> itemCountRows = itemCountsSummary.getItemCountRows();
        for(ItemCountRow itemCountRow : itemCountRows) {
            TextView textView = timeSlotViewMap.get(itemCountRow.getItem());
            textView.setText("(" + Integer.toString(itemCountRow.getCount()) + ")");
            textView.setTextColor(itemCountRow.getColor());
        }
    }

}

package com.morrison.teamworkprojects.ui.donutchart.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.morrison.teamworkprojects.ui.donutchart.manager.Ticker;
import com.morrison.teamworkprojects.ui.donutchart.model.Donut;

public class DonutView extends RelativeLayout implements Ticker.TickListener
{
    private Donut donut;
    private DonutDrawer donutDrawer;

    public DonutView(Context context) {
        super(context);
        invalidate();
    }

    public DonutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        invalidate();
    }

    @Override
    public void tick(final int framesToAdvance) {
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        if(donutDrawer == null) {
            donutDrawer = new DonutDrawer(getWidth(), getHeight());
        }
        super.onDraw(canvas);
        if(donut != null && donut.getDiameterUnit() > 0.0f) {
            donutDrawer.drawDonut(donut, canvas);
        }
    }

    public Donut getDonut() {
        return donut;
    }

    public void setDonut(final Donut donut) {
        this.donut = donut;
    }

}

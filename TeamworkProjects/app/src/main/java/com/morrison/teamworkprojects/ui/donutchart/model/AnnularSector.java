package com.morrison.teamworkprojects.ui.donutchart.model;

public class AnnularSector {
    private float startAngleUnit; // 0.0 to 1.0 goes clockwise from 0 degrees to 360 degrees
    private float sweepAngleUnit; // 0.0 to 1.0 goes clockwise from 0 degrees to 360 degrees
    private int color;

    public AnnularSector(final float startAngleUnit, final float sweepAngleUnit, final int color) {
        this.startAngleUnit = startAngleUnit;
        this.sweepAngleUnit = sweepAngleUnit;
        this.color = color;
    }

    public float getStartAngleUnit() {
        return startAngleUnit;
    }

    public float getSweepAngleUnit() {
        return sweepAngleUnit;
    }

    public int getColor() {
        return color;
    }

}

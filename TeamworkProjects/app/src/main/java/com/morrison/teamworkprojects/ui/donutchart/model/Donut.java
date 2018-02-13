package com.morrison.teamworkprojects.ui.donutchart.model;

import com.morrison.teamworkprojects.model.ColorProvider;
import com.morrison.teamworkprojects.model.ItemCount;

import java.util.ArrayList;
import java.util.List;

public class Donut<T> {
    private List<ItemCount<T>> itemCounts;
    private List<AnnularSector> annularSectors;
    private ColorProvider<T> colorProvider;
    private int totalItemsCount;

    private float diameterUnit; // 1.0 would make the donut diameter the same as the short axis of the containing panel
    private float coreDiameterFractionOfDonutDiameter; // 1.0 would make the core extend all the way to the edge of the donut
    private float rotationUnit; // 0.0 to 1.0 goes clockwise from 0 degrees to 360 degrees
    private float opacity; // 0.0 is fully transparent, 1.0 is fully opaque

    public Donut(final List<ItemCount<T>> itemCounts, final ColorProvider<T> colorProvider) {
        this.itemCounts = itemCounts;
        this.colorProvider = colorProvider;

        initAttributes();
        initAnnularSectors();
    }

    private void initAttributes() {
        totalItemsCount = 0;
        for(ItemCount<T> itemCount : itemCounts) {
            totalItemsCount += itemCount.getCount();
        }

        diameterUnit = 1.0f;
        coreDiameterFractionOfDonutDiameter = 0.75f;
        rotationUnit = 0.0f;
        opacity = 1.0f;
    }

    private void initAnnularSectors() {
        annularSectors = new ArrayList<>();
        float startAngleUnit = 0.0f;
        for(ItemCount<T> itemCount : itemCounts) {
            AnnularSector annularSectorForItemCount = getAnnularSectorForItemCount(itemCount, startAngleUnit);
            annularSectors.add(annularSectorForItemCount);
            startAngleUnit = startAngleUnit + annularSectorForItemCount.getSweepAngleUnit(); // make sure the next sector starts where this one finishes
        }
    }

    private AnnularSector getAnnularSectorForItemCount(final ItemCount<T> itemCount, final float startAngleUnit) {
        float sweepAngleUnit = (float)itemCount.getCount() / (float)totalItemsCount;
        return new AnnularSector(startAngleUnit, sweepAngleUnit, colorProvider.getColor(itemCount.getItem()));
    }

    public List<AnnularSector> getAnnularSectors() {
        return annularSectors;
    }

    public float getDiameterUnit() {
        return diameterUnit;
    }

    public void setDiameterUnit(float diameterUnit) {
        this.diameterUnit = diameterUnit;
    }

    public float getCoreDiameterFractionOfDonutDiameter() {
        return coreDiameterFractionOfDonutDiameter;
    }

    public void setCoreDiameterFractionOfDonutDiameter(float coreDiameterFractionOfDonutDiameter) {
        this.coreDiameterFractionOfDonutDiameter = coreDiameterFractionOfDonutDiameter;
    }

    public float getRotationUnit() {
        return rotationUnit;
    }

    public void setRotationUnit(float rotationUnit) {
        this.rotationUnit = rotationUnit;
    }

    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }

}

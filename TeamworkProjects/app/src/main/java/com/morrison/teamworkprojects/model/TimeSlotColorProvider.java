package com.morrison.teamworkprojects.model;

public class TimeSlotColorProvider implements ColorProvider<TimeSlot> {

    @Override
    public int getColor(final TimeSlot item) {
        return item.getColor();
    }

}

package com.morrison.teamworkprojects.model;

import android.graphics.Color;

public enum TimeSlot {
    LATE("Late", Color.rgb(217, 83, 79)),
    STARTED("Started", Color.rgb(0, 150, 136)),
    TODAY("Today", Color.rgb(140, 193, 248)),
    UPCOMING("Upcoming", Color.rgb(236, 185, 76)),
    NO_DATE("No date", Color.rgb(204, 204, 204));

    private final String name;
    private final int color;

    TimeSlot(final String name, final int color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

}

package com.morrison.teamworkprojects.model;

public interface ColorProvider<T> {

    int getColor(final T item);

}

package com.sorters.fx;

import java.util.ArrayList;
import java.util.Objects;

public class PointList extends ArrayList<Point> {
    @Override
    public Point set(int index, Point element) {
        Objects.checkIndex(index, size());
        final var oldValue = get(index);
        final var newValue = new Point(oldValue.x(), element.y());
        super.set(index, newValue);
        return oldValue;
    }
}

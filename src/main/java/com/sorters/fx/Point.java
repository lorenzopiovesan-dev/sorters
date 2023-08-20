package com.sorters.fx;

public record Point(int x, int y) implements Comparable<Point> {

    public Point(Point point) {
        this(point.x, point.y);
    }
    @Override
    public int compareTo(Point other) {
        return Integer.compare(this.y, other.y);
    }
}

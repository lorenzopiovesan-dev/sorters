package com.sorters.fx;

public record Point(int x, int y) implements Comparable<Point> {
    @Override
    public int compareTo(Point other) {
        return Integer.compare(this.y, other.y);
    }
}

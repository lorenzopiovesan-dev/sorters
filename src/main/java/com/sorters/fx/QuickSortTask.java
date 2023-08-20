package com.sorters.fx;

import com.sorters.options.SortOrder;
import javafx.concurrent.Task;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.random.RandomGenerator;

public class QuickSortTask extends Task<PointList> {

    private final PointList points;
    private final GraphicsContext graphicsContext;

    private final SortOrder sortOrder;
    private final RandomGenerator randomGenerator;

    public QuickSortTask(PointList points, GraphicsContext graphicsContext, RandomGenerator randomGenerator, SortOrder sortOrder) {
        this.points = points;
        this.graphicsContext = graphicsContext;
        this.sortOrder = sortOrder;
        this.randomGenerator = randomGenerator;
    }

    @Override
    protected PointList call() {
        quickSort(this.points, 0, this.points.size() - 1);
        return this.points;
    }

    private void quickSort(PointList list, int start, int end) {
        if (start < end) {
            int p = getPartition(list, start, end);
            quickSort(list, start, p);
            quickSort(list, p + 1, end);
        }
    }

    public int getPartition(PointList list, int start, int end) {

        final var pivotIndex = this.randomGenerator.nextInt(end - start) + start;
        final var pivot = list.get(pivotIndex);

        while (start <= end) {
            final var elementA = list.get(start);
            final var elementB = list.get(end);

            final var swapStart = deriveSwapStart(elementA, pivot, this.sortOrder);
            final var swapEnd = deriveSwapEnd(elementB, pivot, this.sortOrder);

            if (!swapStart) {
                start++;
            }
            if (!swapEnd) {
                end--;
            }

            if (isSwappable(swapStart, swapEnd)) {

                highlightPoints(elementA, elementB, Color.RED, 5.0);

                list.set(start, elementB);
                list.set(end, elementA);

                transitionToNewPoints(list, start, end, elementA, elementB);

                start++;
                end--;
            }
        }
        return start - 1;
    }

    private void transitionToNewPoints(PointList list, int start, int end, Point elementA, Point elementB) {
        final var newElementA = list.get(start);
        final var newElementB = list.get(end);
        clearHighlightedPoints(elementA, elementB);
        highlightPoints(newElementA, newElementB, Color.BLUE, 2.0);
    }

    private void clearHighlightedPoints(Point elementA, Point elementB) {
        try {
            Thread.sleep(10);
            graphicsContext.clearRect(elementA.x(), elementA.y(), 5.0, 5.0);
            graphicsContext.clearRect(elementB.x(), elementB.y(), 5.0, 5.0);
        } catch (InterruptedException e) {
            System.out.println("sleeping");
        }
    }

    private void highlightPoints(Point elementA, Point elementB, Color color, double radius) {
        graphicsContext.setFill(color);
        graphicsContext.fillOval(elementA.x(), elementA.y(), radius, radius);
        graphicsContext.fillOval(elementB.x(), elementB.y(), radius, radius);
    }

    private boolean deriveSwapStart(Point element, Point pivot, SortOrder sortOrder) {
        return switch (sortOrder) {
            case ASCENDING -> isGreaterOrEqual(element, pivot);
            case DESCENDING -> isLesserOrEqual(element, pivot);
        };
    }

    private boolean deriveSwapEnd(Point element, Point pivot, SortOrder sortOrder) {
        return switch (sortOrder) {
            case ASCENDING -> isLesserOrEqual(element, pivot);
            case DESCENDING -> isGreaterOrEqual(element, pivot);
        };
    }

    private boolean isGreaterOrEqual(Point a, Point b) {
        return a.compareTo(b) >= 0;
    }

    private boolean isLesserOrEqual(Point a, Point b) {
        return a.compareTo(b) <= 0;
    }

    private boolean isSwappable(boolean s1, boolean s2) {
        return s1 && s2;
    }
}

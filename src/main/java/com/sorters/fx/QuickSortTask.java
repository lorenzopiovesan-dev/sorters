package com.sorters.fx;

import com.sorters.options.SortOrder;
import com.sorters.quicksort.QuickSort;
import com.sorters.quicksort.config.Action;
import com.sorters.quicksort.config.Sorter;
import com.sorters.quicksort.partitioners.HoarePartitioner;
import com.sorters.quicksort.partitioners.config.HoarePartitionerConfig;
import com.sorters.quicksort.partitioners.config.HoarePartitionerHandler;
import javafx.concurrent.Task;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Comparator;
import java.util.List;
import java.util.random.RandomGenerator;

public class QuickSortTask extends Task<PointList> {

    private final PointList points;
    private final GraphicsContext graphicsContext;

    private final SortOrder sortOrder;

    private final Sorter<Point> sorter;

    public QuickSortTask(PointList points, GraphicsContext graphicsContext, RandomGenerator randomGenerator, SortOrder sortOrder) {
        this.points = points;
        this.graphicsContext = graphicsContext;
        this.sortOrder = sortOrder;
        final var partitioner = new HoarePartitioner<Point>(randomGenerator);
        this.sorter = new QuickSort<>(partitioner);
    }

    @Override
    protected PointList call() {

        final Action<Point> before = (oldElementA, oldElementB, list, start, end) -> highlightPoints(oldElementA, oldElementB, Color.RED, 5.0);

        final Action<Point> after = this::transitionToNewPoints;
        final var comparator = new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Integer.compare(o1.y(), o2.y());
            }
        };
        final var partitionerHandler = new HoarePartitionerHandler<>(before, after, comparator);
        final var config = new HoarePartitionerConfig<>(this.sortOrder, partitionerHandler);

        this.sorter.sort(this.points, config);
        return this.points;
    }

    private void transitionToNewPoints(Point oldElementA, Point oldElementB, List<Point> list, int start, int end) {
        clearHighlightedPoints(oldElementA, oldElementB);
        final var newElementA = list.get(start);
        final var newElementB = list.get(end);
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

}

package com.sorters.quicksort.partitioners.config;

import com.sorters.quicksort.config.Action;
import com.sorters.quicksort.config.PartitionerHandler;

import java.util.Comparator;

public class HoarePartitionerHandler<T> implements PartitionerHandler<T> {

    private final Action<T> beforeSwapAction;
    private final Action<T> afterSwapAction;
    private final Comparator<T> comparator;

    public HoarePartitionerHandler(Action<T> beforeSwapAction, Action<T> afterSwapAction, Comparator<T> comparator) {
        this.beforeSwapAction = beforeSwapAction;
        this.afterSwapAction = afterSwapAction;
        this.comparator = comparator;
    }

    @Override
    public Action<T> performBeforeSwap() {
        return this.beforeSwapAction;
    }

    @Override
    public Action<T> performAfterSwap() {
        return this.afterSwapAction;
    }

    @Override
    public Comparator<T> getComparator() {
        return this.comparator;
    }
}

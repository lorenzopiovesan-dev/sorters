package com.sorters.quicksort;

import com.sorters.options.SortOrder;

import java.util.Comparator;

public class HoarePartitionerConfig<T> implements PartitionerConfig<T> {

    private final SortOrder sortOrder;
    private final PartitionerHandler<T> partitionerHandler;

    public HoarePartitionerConfig(SortOrder sortOrder, PartitionerHandler<T> partitionerHandler) {
        this.sortOrder = sortOrder;
        this.partitionerHandler = partitionerHandler;
    }

    @Override
    public SortOrder getSortOrder() {
        return this.sortOrder;
    }

    @Override
    public Action<T> getActionBeforeSwap() {
        return this.partitionerHandler.performBeforeSwap();
    }

    @Override
    public Action<T> getActionAfterSwap() {
        return this.partitionerHandler.performAfterSwap();
    }

    @Override
    public Comparator<T> getComparator() {
        return this.partitionerHandler.getComparator();
    }
}

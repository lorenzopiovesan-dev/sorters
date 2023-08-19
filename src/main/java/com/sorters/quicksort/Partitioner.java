package com.sorters.quicksort;

import com.sorters.options.SortOrder;

import java.util.List;

public interface Partitioner<T extends Comparable<T>> {
    int getPartition(List<T> list, int start, int end, SortOrder sortOrder);
}

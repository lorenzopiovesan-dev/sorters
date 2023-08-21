package com.sorters.quicksort;

import com.sorters.options.SortOrder;

import java.util.Comparator;
import java.util.List;

public interface Partitioner<T> {
    int getPartition(List<T> list, int start, int end, PartitionerConfig<T> config);
}

package com.sorters.quicksort.config;

import java.util.List;

public interface Partitioner<T> {
    int getPartition(List<T> list, int start, int end, PartitionerConfig<T> config);
}

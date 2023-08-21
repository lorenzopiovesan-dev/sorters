package com.sorters.quicksort.config;

import com.sorters.quicksort.config.PartitionerConfig;

import java.util.List;

public interface Partitioner<T> {
    int getPartition(List<T> list, int start, int end, PartitionerConfig<T> config);
}

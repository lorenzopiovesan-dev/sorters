package com.sorters.quicksort;

import com.sorters.quicksort.config.PartitionerConfig;
import com.sorters.quicksort.config.Sorter;
import com.sorters.quicksort.config.Partitioner;
import jakarta.inject.Inject;

import java.util.List;

public class QuickSort<T extends Comparable<T>> implements Sorter<T> {

    private final Partitioner<T> partitioner;

    @Inject
    public QuickSort(Partitioner<T> partitioner) {
        this.partitioner = partitioner;
    }

    @Override
    public void sort(List<T> unsortedList, PartitionerConfig<T> config) {
        if (unsortedList == null) {
            throw new RuntimeException("Null list not allowed");
        }
        sort(unsortedList, 0, unsortedList.size() - 1, config);
    }

    private void sort(List<T> list, int start, int end, PartitionerConfig<T> config) {
        if (start < end) {
            int p = this.partitioner.getPartition(list, start, end, config);
            sort(list, start, p, config);
            sort(list, p + 1, end, config);
        }
    }

}

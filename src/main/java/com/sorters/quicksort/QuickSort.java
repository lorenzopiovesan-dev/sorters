package com.sorters.quicksort;

import com.sorters.options.SortOrder;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuickSort<T extends Comparable<T>> {

    private final Partitioner<T> partitioner;

    @Inject
    public QuickSort(Partitioner<T> partitioner) {
        this.partitioner = partitioner;
    }

    /**
     * Given a generic unsorted list of elements T, this method will return a new, sorted, instance of such list
     *
     * @param unsortedList the original list to sort
     * @param sortOrder    the order in which the sorting must take place ({@link com.sorters.options.SortOrder#ASCENDING} or {@link com.sorters.options.SortOrder#DESCENDING})
     * @return a new instance of the sorted list
     * @see com.sorters.options.SortOrder
     */
    public List<T> sort(List<T> unsortedList, SortOrder sortOrder) {
        if (unsortedList == null) {
            throw new RuntimeException("Null list not allowed");
        }
        if (unsortedList.isEmpty()) {
            return Collections.emptyList();
        }
        final var sortedList = new ArrayList<>(unsortedList);
        sort(sortedList, 0, sortedList.size() - 1, sortOrder);
        return sortedList;
    }

    private void sort(List<T> list, int start, int end, SortOrder sortOrder) {
        if (start < end) {
            int p = this.partitioner.getPartition(list, start, end, sortOrder);
            sort(list, start, p, sortOrder);
            sort(list, p + 1, end, sortOrder);
        }
    }

}

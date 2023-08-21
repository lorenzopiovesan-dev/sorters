package com.sorters.quicksort.config;

import java.util.List;

public interface Sorter<T extends Comparable<T>> {
    /**
     * Given a generic unsorted list of elements T, this method will return a new, sorted, instance of such list
     *
     * @param unsortedList the original list to sort
     * @param sortOrder    the order in which the sorting must take place ({@link com.sorters.options.SortOrder#ASCENDING} or {@link com.sorters.options.SortOrder#DESCENDING})
     * @return a new instance of the sorted list
     * @see com.sorters.options.SortOrder
     */
    void sort(List<T> unsortedList, PartitionerConfig<T> config);
}

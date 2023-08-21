package com.sorters.quicksort;

import com.sorters.options.SortOrder;

import java.util.Comparator;

public interface PartitionerConfig<T> {

    SortOrder getSortOrder();
    Action<T> getActionBeforeSwap();
    Action<T> getActionAfterSwap();
    Comparator<T> getComparator();
}

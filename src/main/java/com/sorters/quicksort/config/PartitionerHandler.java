package com.sorters.quicksort.config;

import java.util.Comparator;

public interface PartitionerHandler<T> {
    Action<T> performBeforeSwap();
    Action<T> performAfterSwap();
    Comparator<T> getComparator();
}

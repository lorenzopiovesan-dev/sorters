package com.sorters.quicksort;

import java.util.Comparator;

public interface PartitionerHandler<T> {
    Action<T> performBeforeSwap();
    Action<T> performAfterSwap();
    Comparator<T> getComparator();
}

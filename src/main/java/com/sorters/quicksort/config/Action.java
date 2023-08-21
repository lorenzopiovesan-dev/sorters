package com.sorters.quicksort.config;

import java.util.List;

public interface Action <T> {
    void perform(T oldElementA, T oldElementB, List<T> partiallySorted, int currentStart, int currentEnd);
}

package com.sorters;

import java.util.Collections;
import java.util.List;

public class QuickSort {

    public List<Integer> sort(List<Integer> numList) {
        if (numList == null) {
            throw new RuntimeException("Null list not allowed");
        }
        if (numList.isEmpty()) {
            return Collections.emptyList();
        }
        return null;
    }
}

package com.sorters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuickSort {

    public List<Integer> sort(List<Integer> numList) {
        if (numList == null) {
            throw new RuntimeException("Null list not allowed");
        }
        if (numList.isEmpty()) {
            return Collections.emptyList();
        }
        final var list = new ArrayList<>(numList);
        sort(list, 0, numList.size() - 1);
        return list;
    }

    private void sort(List<Integer> list, int start, int end) {
        if (start < end) {
            int p = partition(list, start, end);
            sort(list, start, p);
            sort(list, p + 1, end);
        }
    }

    private int partition(List<Integer> list, int start, int end) {

        final var pivotIndex = new Random().nextInt(end - start) + start;
        final var pivot = list.get(pivotIndex);

        boolean swapStart;
        boolean swapEnd;

        while (start <= end) {
            final var elementA = list.get(start);
            final var elementB = list.get(end);

            swapStart = isGreaterOrEqual(elementA, pivot);
            if (!swapStart) {
                start++;
            }

            swapEnd = isLesserOrEqual(elementB, pivot);
            if (!swapEnd) {
                end--;
            }

            if (isSwappable(swapStart, swapStart)) {
                list.set(start, elementB);
                list.set(end, elementA);
                start++;
                end--;
            }
        }
        return start - 1;
    }

    private boolean isGreaterOrEqual(Integer a, Integer b) {
        return a.compareTo(b) >= 0;
    }

    private boolean isLesserOrEqual(Integer a, Integer b) {
        return a.compareTo(b) <= 0;
    }

    private boolean isSwappable(boolean s1, boolean s2) {
        return s1 && s2;
    }
}

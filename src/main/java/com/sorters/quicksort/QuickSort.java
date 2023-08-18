package com.sorters.quicksort;

import com.sorters.options.SortOrder;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.random.RandomGenerator;

public class QuickSort<T extends Comparable<T>> {

    private final RandomGenerator randomGenerator;

    @Inject
    public QuickSort(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

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
            int p = partition(list, start, end, sortOrder);
            sort(list, start, p, sortOrder);
            sort(list, p + 1, end, sortOrder);
        }
    }

    private int partition(List<T> list, int start, int end, SortOrder sortOrder) {

        final var pivotIndex = this.randomGenerator.nextInt(end - start) + start;
        final var pivot = list.get(pivotIndex);

        while (start <= end) {
            final var elementA = list.get(start);
            final var elementB = list.get(end);

            final var swapStart = deriveSwapStart(elementA, pivot, sortOrder);
            final var swapEnd = deriveSwapEnd(elementB, pivot, sortOrder);

            if (!swapStart) {
                start++;
            }
            if (!swapEnd) {
                end--;
            }

            if (isSwappable(swapStart, swapEnd)) {
                list.set(start, elementB);
                list.set(end, elementA);
                start++;
                end--;
            }
        }
        return start - 1;
    }

    private boolean deriveSwapStart(T element, T pivot, SortOrder sortOrder) {
        return switch (sortOrder) {
            case ASCENDING -> isGreaterOrEqual(element, pivot);
            case DESCENDING -> isLesserOrEqual(element, pivot);
        };
    }

    private boolean deriveSwapEnd(T element, T pivot, SortOrder sortOrder) {
        return switch (sortOrder) {
            case ASCENDING -> isLesserOrEqual(element, pivot);
            case DESCENDING -> isGreaterOrEqual(element, pivot);
        };
    }

    private boolean isGreaterOrEqual(T a, T b) {
        return a.compareTo(b) >= 0;
    }

    private boolean isLesserOrEqual(T a, T b) {
        return a.compareTo(b) <= 0;
    }

    private boolean isSwappable(boolean s1, boolean s2) {
        return s1 && s2;
    }
}

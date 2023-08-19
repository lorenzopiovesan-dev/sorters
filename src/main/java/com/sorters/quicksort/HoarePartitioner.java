package com.sorters.quicksort;

import com.sorters.options.SortOrder;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.random.RandomGenerator;

@Singleton
public class HoarePartitioner<T extends Comparable<T>> implements Partitioner<T> {

    private final RandomGenerator randomGenerator;

    @Inject
    public HoarePartitioner(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    @Override
    public int getPartition(List<T> list, int start, int end, SortOrder sortOrder) {

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

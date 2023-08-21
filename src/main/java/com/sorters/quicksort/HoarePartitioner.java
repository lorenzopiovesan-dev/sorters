package com.sorters.quicksort;

import com.sorters.options.SortOrder;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Comparator;
import java.util.List;
import java.util.random.RandomGenerator;

@Singleton
public class HoarePartitioner<T> implements Partitioner<T> {

    private final RandomGenerator randomGenerator;

    @Inject
    public HoarePartitioner(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    @Override
    public int getPartition(List<T> list, int start, int end, PartitionerConfig<T> config) {

        final var pivotIndex = this.randomGenerator.nextInt(end - start) + start;
        final var pivot = list.get(pivotIndex);

        while (start <= end) {
            final var elementA = list.get(start);
            final var elementB = list.get(end);

            final var swapStart = deriveSwapStart(elementA, pivot, config.getSortOrder(), config.getComparator());
            final var swapEnd = deriveSwapEnd(elementB, pivot, config.getSortOrder(), config.getComparator());

            if (!swapStart) {
                start++;
            }
            if (!swapEnd) {
                end--;
            }

            if (isSwappable(swapStart, swapEnd)) {
                performAction(config.getActionBeforeSwap(), elementA, elementB, list, start, end);
                list.set(start, elementB);
                list.set(end, elementA);
                performAction(config.getActionAfterSwap(), elementA, elementB, list, start, end);
                start++;
                end--;
            }
        }
        return start - 1;
    }

    private void performAction(Action<T> action, T oldElementA, T oldElementB, List<T> list, int start, int end) {
        if (action != null) {
            action.perform(oldElementA, oldElementB, list, start, end);
        }
    }

    private boolean deriveSwapStart(T element, T pivot, SortOrder sortOrder, Comparator<T> comparator) {
        return switch (sortOrder) {
            case ASCENDING -> isGreaterOrEqual(element, pivot, comparator);
            case DESCENDING -> isLesserOrEqual(element, pivot, comparator);
        };
    }

    private boolean deriveSwapEnd(T element, T pivot, SortOrder sortOrder, Comparator<T> comparator) {
        return switch (sortOrder) {
            case ASCENDING -> isLesserOrEqual(element, pivot, comparator);
            case DESCENDING -> isGreaterOrEqual(element, pivot, comparator);
        };
    }

    private boolean isGreaterOrEqual(T a, T b, Comparator<T> comparator) {
        return comparator.compare(a, b) >= 0;
    }

    private boolean isLesserOrEqual(T a, T b, Comparator<T> comparator) {
        return comparator.compare(a, b) <= 0;
    }

    private boolean isSwappable(boolean s1, boolean s2) {
        return s1 && s2;
    }
}

package com.sorters;

import java.util.*;

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

        boolean s1;
        boolean s2;

        while (start <= end) {
            s1 = false;
            s2 = false;
            if (list.get(start).compareTo(pivot) >= 0) {
                s1 = true;
            } else {
                start++;
            }
            if (list.get(end).compareTo(pivot) <= 0) {
                s2 = true;
            } else {
                end--;
            }
            if (s1 && s2) {
                final var temp = list.get(start);
                list.set(start, list.get(end));
                list.set(end, temp);
                start++;
                end--;
            }
        }
        return start - 1;
    }
}

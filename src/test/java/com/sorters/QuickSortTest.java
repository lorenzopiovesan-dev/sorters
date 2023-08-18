package com.sorters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class QuickSortTest {

    @Test
    @DisplayName("Provided a null argument, an exception is thrown")
    public void sort_test_1() {
        // Arrange
        final var quicksort = new QuickSort();

        // Act
        final var sorted = Assertions.assertThrows(RuntimeException.class, () -> quicksort.sort(null));

        // Assert
        Assertions.assertEquals("Null list not allowed", sorted.getMessage());
    }

    @Test
    @DisplayName("Provided an empty list of numbers, an empty list is returned")
    public void sort_test_2() {
        // Arrange
        final List<Integer> numList = List.of();
        final var quicksort = new QuickSort();

        // Act
        final var sorted = quicksort.sort(numList);

        // Assert
        Assertions.assertTrue(sorted.isEmpty());
    }

    @Test
    @DisplayName("Provided a list of numbers, this is sorted from min to max")
    public void sort_test_3() {
        // Arrange
        final var numList = List.of(4,7,1,2,0,2,5,4,-1);
        final var quicksort = new QuickSort();

        // Act
        final var sorted = quicksort.sort(numList);

        // Assert
        int current = Integer.MIN_VALUE;
        for (final var number : sorted) {
            Assertions.assertTrue(number >= current);
            current = number;
        }
    }

}

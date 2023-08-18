package com.sorters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

}

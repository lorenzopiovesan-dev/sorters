package com.sorters.quicksort;

import com.sorters.options.SortOrder;
import com.sorters.injection.RandomGeneratorProvider;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

public class QuickSortTest {

    private static RandomGenerator randomGenerator;

    @BeforeAll
    static void setUp() {
        randomGenerator = new RandomGeneratorProvider().get();
    }

    @Test
    @DisplayName("Once instantiated the Random Generator Provider a Random.class instance is returned")
    public void instantiation_test() {
        // Arrange
        final var provider = new RandomGeneratorProvider();

        // Act
        final var random = provider.get();

        // Assert
        Assertions.assertInstanceOf(Random.class, random);
    }

    @Test
    @DisplayName("Provided a null argument, an exception is thrown")
    public void sort_test_1() {
        // Arrange
        final var quicksort = new QuickSort<Integer>(randomGenerator);

        // Act
        final var sorted = Assertions.assertThrows(RuntimeException.class, () -> quicksort.sort(null, SortOrder.ASCENDING));

        // Assert
        Assertions.assertEquals("Null list not allowed", sorted.getMessage());
    }

    @Test
    @DisplayName("Provided an empty list of numbers, an empty list is returned")
    public void sort_test_2() {
        // Arrange
        final List<Integer> numList = List.of();
        final var quicksort = new QuickSort<Integer>(randomGenerator);

        // Act
        final var sorted = quicksort.sort(numList, SortOrder.ASCENDING);

        // Assert
        Assertions.assertTrue(sorted.isEmpty());
    }

    @Test
    @DisplayName("Provided a list of numbers, this is sorted from MIN to MAX")
    public void sort_test_3() {
        // Arrange
        final var numList = List.of(4,7,1,2,0,2,5,4,-1);
        final var quicksort = new QuickSort<Integer>(randomGenerator);

        // Act
        final var sorted = quicksort.sort(numList, SortOrder.ASCENDING);

        // Assert
        int current = Integer.MIN_VALUE;
        for (final var number : sorted) {
            Assertions.assertTrue(number >= current);
            current = number;
        }
    }

    @Test
    @DisplayName("Provided a list of numbers, this is sorted from MAX to MIN")
    public void sort_test_4() {
        // Arrange
        final var numList = List.of(4,7,1,2,0,2,5,4,-1);
        final var quicksort = new QuickSort<Integer>(randomGenerator);

        // Act
        final var sorted = quicksort.sort(numList, SortOrder.DESCENDING);

        // Assert
        int current = Integer.MAX_VALUE;
        for (final var number : sorted) {
            Assertions.assertTrue(number <= current);
            current = number;
        }
    }

}

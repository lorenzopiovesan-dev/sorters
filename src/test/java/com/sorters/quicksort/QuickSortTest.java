package com.sorters.quicksort;

import com.sorters.injection.RandomGeneratorProvider;
import com.sorters.options.SortOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuickSortTest {

    private static Partitioner<Integer> partitioner;
    private static PartitionerHandler<Integer> partitionerHandler;
    private static PartitionerConfig<Integer> partitionerConfig;


    @BeforeAll
    static void setUp() {
        final var randomGenerator = new RandomGeneratorProvider().get();
        partitioner = new HoarePartitioner<>(randomGenerator);
        partitionerHandler = new HoarePartitionerHandler<>(null, null, Integer::compareTo);
        partitionerConfig = new HoarePartitionerConfig<>(SortOrder.ASCENDING, partitionerHandler);

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
        final var quicksort = new QuickSort<>(partitioner);

        // Act
        final var sorted = Assertions.assertThrows(RuntimeException.class, () -> quicksort.sort(null, partitionerConfig));

        // Assert
        Assertions.assertEquals("Null list not allowed", sorted.getMessage());
    }

    @Test
    @DisplayName("Provided an empty list of numbers, an empty list is returned")
    public void sort_test_2() {
        // Arrange
        final List<Integer> numList = List.of();
        final var quicksort = new QuickSort<>(partitioner);

        // Act
        quicksort.sort(numList, partitionerConfig);

        // Assert
        Assertions.assertTrue(numList.isEmpty());
    }

    @Test
    @DisplayName("Provided a list of numbers, this is sorted from MIN to MAX")
    public void sort_test_3() {
        // Arrange
        final var numList = new ArrayList<>(List.of(4, 7, 1, 2, 0, 2, 5, 4, -1));
        final var quicksort = new QuickSort<>(partitioner);

        // Act
        quicksort.sort(numList, partitionerConfig);

        // Assert
        int current = Integer.MIN_VALUE;
        for (final var number : numList) {
            Assertions.assertTrue(number >= current);
            current = number;
        }
    }

    @Test
    @DisplayName("Provided a list of numbers, this is sorted from MAX to MIN")
    public void sort_test_4() {
        // Arrange
        final var numList = new ArrayList<>(List.of(4, 7, 1, 2, 0, 2, 5, 4, -1));
        final var quicksort = new QuickSort<>(partitioner);
        final var descendingConfig = new HoarePartitionerConfig<>(SortOrder.DESCENDING, partitionerHandler);

        // Act
        quicksort.sort(numList, descendingConfig);

        // Assert
        int current = Integer.MAX_VALUE;
        for (final var number : numList) {
            Assertions.assertTrue(number <= current);
            current = number;
        }
    }

}

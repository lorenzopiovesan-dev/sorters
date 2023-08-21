package com.sorters;

import com.google.inject.Guice;
import com.google.inject.Key;
import com.sorters.injection.SortersDIModule;
import com.sorters.options.SortOrder;
import com.sorters.quicksort.partitioners.config.HoarePartitionerConfig;
import com.sorters.quicksort.partitioners.config.HoarePartitionerHandler;
import com.sorters.quicksort.QuickSort;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final var modules = List.of(new SortersDIModule());
        final var injector = Guice.createInjector(modules);
        final var quicksort = injector.getInstance(new Key<QuickSort<Integer>>() {
        });

        final var hoarePartitionerHandler = new HoarePartitionerHandler<>(null, null, Integer::compareTo);
        final var hoarePartitionerConfig = new HoarePartitionerConfig<Integer>(SortOrder.ASCENDING, hoarePartitionerHandler);

        final var numList = new ArrayList<>(List.of(4, 7, 1, 2, 0, 2, 5, 4, -1));
        quicksort.sort(numList, hoarePartitionerConfig);
        numList.forEach(System.out::println);
    }
}
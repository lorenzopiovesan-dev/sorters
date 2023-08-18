package com.sorters;

import com.google.inject.Guice;
import com.google.inject.Key;
import com.sorters.injection.SortersDIModule;
import com.sorters.options.SortOrder;
import com.sorters.quicksort.QuickSort;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        final var modules = List.of(new SortersDIModule());
        final var injector = Guice.createInjector(modules);
        final var quicksort = injector.getInstance(new Key<QuickSort<Integer>>(){});

        final var numList = List.of(4,7,1,2,0,2,5,4,-1);
        final var sorted = quicksort.sort(numList, SortOrder.DESCENDING);
        sorted.forEach(System.out::println);
    }
}
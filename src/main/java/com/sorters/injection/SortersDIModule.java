package com.sorters.injection;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.sorters.quicksort.HoarePartitioner;
import com.sorters.quicksort.Partitioner;

import java.util.random.RandomGenerator;

public class SortersDIModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(RandomGenerator.class).toProvider(RandomGeneratorProvider.class);
        bind(new TypeLiteral<Partitioner<Integer>>() {
        }).to(new TypeLiteral<HoarePartitioner<Integer>>() {
        });
    }
}

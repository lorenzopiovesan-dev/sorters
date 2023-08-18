package com.sorters.injection;

import com.google.inject.AbstractModule;

import java.util.random.RandomGenerator;

public class SortersDIModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(RandomGenerator.class).toProvider(RandomGeneratorProvider.class);
    }
}

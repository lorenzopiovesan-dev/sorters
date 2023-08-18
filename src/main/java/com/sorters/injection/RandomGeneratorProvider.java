package com.sorters.injection;

import jakarta.inject.Provider;
import jakarta.inject.Singleton;

import java.util.Random;
import java.util.random.RandomGenerator;

@Singleton
public class RandomGeneratorProvider implements Provider<RandomGenerator> {
    @Override
    public RandomGenerator get() {
        return new Random();
    }
}

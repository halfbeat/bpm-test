package es.jcyl.gss.redmine;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;
import java.util.function.Supplier;

class RandomCollection<E> implements Supplier<E> {
    private final NavigableMap<Double, E> map = new TreeMap<>();
    private final Random random;
    private double total = 0;

    RandomCollection() {
        this(new Random());
    }

    RandomCollection(Random random) {
        this.random = random;
    }

    void add(double weight, E result) {
        if (weight <= 0) return;
        total += weight;
        map.put(total, result);
    }

    E next() {
        double value = random.nextDouble() * total;
        return map.higherEntry(value).getValue();
    }

    @Override
    public E get() {
        return next();
    }
}
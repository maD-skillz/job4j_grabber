package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {

    public <T> T kiss(List<T> value, Comparator<T> comparator, Predicate<Integer> pred) {
        T val = value.get(0);
        for (T index : value) {
            if (pred.test(comparator.compare(val, index))) {
                val = index;
            }
        }
        return val;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> pred = p -> p < 0;
        return kiss(value, comparator, pred);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> pred = p -> p > 0;
        return kiss(value, comparator, pred);
    }
}

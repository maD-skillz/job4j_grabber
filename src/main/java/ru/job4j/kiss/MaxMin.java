package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {

    public <T> T kiss(List<T> value, Comparator<T> comparator, int costyl) {
        T val = value.get(0);
        for (T index : value) {
            if (costyl == 1) {
                if (comparator.compare(val, index) < 0) {
                    val = index;
                }
            } else if (costyl == -1) {
                if (comparator.compare(val, index) > 0) {
                    val = index;
                }
            }
        }
        return val;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return kiss(value, comparator, 1);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return kiss(value, comparator, -1);
    }
}

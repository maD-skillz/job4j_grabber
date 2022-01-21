package ru.job4j.kiss;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class MaxMinTest {

    @Test
    public void maxTest() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(3, 5, 1, 4, 2);
        assertThat(maxMin.max(list, Comparator.naturalOrder()), is(5));
    }

    @Test
    public void minTest() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(5, 3, 1, 4, 2);
        assertThat(maxMin.min(list, Comparator.naturalOrder()), is(1));
    }

}
package ru.job4j.design.lsp;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ControlQualityTest {

    @Test
    public void whenFresh() {

    }

    @Test
    public void whenExpiredBetween25and75() {

    }

    @Ignore
    @Test
    public void whenExpired() {
        Distributor trash = new Trash();
        ControlQuality controlQuality = new ControlQuality();
        Food apple = new Apple("Apple", new GregorianCalendar(2022, Calendar.MARCH, 5),
                new GregorianCalendar(2022, Calendar.MARCH, 5),
                75, 0);
        controlQuality.distribution(apple);
        Food result = trash.getFood(apple);
        assertThat(result, is(apple));
    }

}
package ru.job4j.design.lsp;

import org.junit.Ignore;
import org.junit.Test;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ControlQualityTest {

    @Test
    public void whenFresh() {
        Warehouse warehouse = new Warehouse();
        LocalDateTime expired = LocalDateTime.now().plusDays(30);
        LocalDateTime created = LocalDateTime.now().minusDays(5);
        Food tuna = new Tuna("Tuna", expired, created, 150, 0);
        List<Distributor> list = new ArrayList<>();
        list.add(warehouse);
        ControlQuality cq = new ControlQuality(list);
        cq.distribution(tuna);
        Food result = null;
        for (Food i : warehouse.getCopyOfStore()) {
            result = i;
        }
        assertThat(result, is(tuna));
    }

    @Test
    public void whenExpiredBetween25and75() {
        Shop shop = new Shop();
        LocalDateTime expired = LocalDateTime.now().plusDays(40);
        LocalDateTime created = LocalDateTime.now().minusDays(30);
        Food chicken = new Chicken("Chicken", expired, created, 100, 0);
        List<Distributor> list = new ArrayList<>();
        list.add(shop);
        ControlQuality cq = new ControlQuality(list);
        cq.distribution(chicken);
        Food result = null;
        for (Food i : shop.getCopyOfStore()) {
            result = i;
        }
        assertThat(result, is(chicken));
    }

    @Ignore
    @Test
    public void whenApplyDiscount() {
        Shop shop = new Shop();
        LocalDateTime expired = LocalDateTime.now().plusDays(2);
        LocalDateTime created = LocalDateTime.now().minusDays(50);
        Food chicken = new Chicken("Chicken", expired, created, 100, 45);
        List<Distributor> list = new ArrayList<>();
        list.add(shop);
        ControlQuality cq = new ControlQuality(list);
        cq.distribution(chicken);
        double result = 0;
        for (Food i : shop.getCopyOfStore()) {
            result = i.getPrice();
        }
        assertThat(result, is(55.0));
    }

    @Test
    public void whenExpired() {
        Trash trash = new Trash();
        LocalDateTime expired = LocalDateTime.now().minusDays(1);
        LocalDateTime created = LocalDateTime.now().minusDays(30);
        Food apple = new Apple("Apple", expired, created, 70, 0);
        trash.addFood(apple);
        Food result = null;
        for (Food i : trash.getCopyOfStore()) {
            result = i;
        }
        assertThat(result, is(apple));
    }

    @Test
    public void whenExpired2() {
        Trash trash = new Trash();
        LocalDateTime expired = LocalDateTime.now().minusDays(1);
        LocalDateTime created = LocalDateTime.now().minusDays(30);
        Food apple = new Apple("Apple", expired, created, 70, 0);
        List<Distributor> list = new ArrayList<>();
        list.add(trash);
        ControlQuality cq = new ControlQuality(list);
        cq.distribution(apple);
        Food result = null;
        for (Food i : trash.getCopyOfStore()) {
            result = i;
        }
        assertThat(result, is(apple));
    }

}
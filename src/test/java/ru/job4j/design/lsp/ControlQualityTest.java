package ru.job4j.design.lsp;

import org.junit.Ignore;
import org.junit.Test;


import java.time.LocalDateTime;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ControlQualityTest {
    @Ignore
    @Test
    public void whenFresh() {
        Warehouse warehouse = new Warehouse();
        ControlQuality cq = new ControlQuality();
        LocalDateTime expired = LocalDateTime.of(2022, 12, 20, 0, 0);
        LocalDateTime created = LocalDateTime.of(2022, 1, 13, 0, 0);
        Food tuna = new Tuna("Tuna", expired, created, 150, 0);
        Food result = null;
        cq.distribution(tuna);
        for (Food i : warehouse.getWarehouseStore()) {
            result = i;
        }
        assertThat(result, is(tuna));

    }
    @Ignore
    @Test
    public void whenExpiredBetween25and75() {
        Shop shop = new Shop();
        LocalDateTime expired = LocalDateTime.of(2022, 8, 20, 0, 0);
        LocalDateTime created = LocalDateTime.of(2021, 8, 13, 0, 0);
        Food chicken = new Chicken("Chicken", expired, created, 100, 0);
        Food result = null;
        shop.addFood(chicken);
        for (Food i : shop.getShopStore()) {
            result = i;
        }
        assertThat(result, is(chicken));
    }

    @Test
    public void whenExpired() {
        Trash trash = new Trash();
        LocalDateTime expired = LocalDateTime.of(2022, 1, 13, 0, 0);
        LocalDateTime created = LocalDateTime.of(2021, 1, 13, 0, 0);
        Food apple = new Apple("Apple", expired, created, 70, 0);
        trash.addFood(apple);
        Food result = null;
        for (Food i : trash.getTrashStore()) {
            result = i;
        }
        assertThat(result, is(apple));
    }

}
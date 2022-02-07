package ru.job4j.design.lsp;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Shop implements Distributor {

    private final List<Food> shopStore = new ArrayList<>();

    public List<Food> getShopStore() {
        return shopStore;
    }

    @Override
    public boolean accept(Food food) {
        boolean result = false;
        Duration expirationDateDur = Duration.between(food.getExpiryDate(), food.getCreateDate());
        Duration residualExpTimeDur = Duration.between(LocalDateTime.now(), food.getCreateDate());
        long expirationDate = Math.abs(expirationDateDur.toDays());
        long residualExpTime = Math.abs(residualExpTimeDur.toDays());
        long percent = (residualExpTime / expirationDate) * 100;
        if (percent >= 25 && percent <= 75) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean addFood(Food food) {
        boolean result = false;
        if (accept(food)) {
            result = shopStore.add(food);
        }
        return result;
    }
}

package ru.job4j.design.lsp;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Distributor {

    private final List<Food> warehouseStore = new ArrayList<>();

    public List<Food> getWarehouseStore() {
        return warehouseStore;
    }

    @Override
    public boolean accept(Food food) {
        boolean result = false;
        Duration expirationDateDur = Duration.between(food.getCreateDate(), food.getExpiryDate());
        Duration residualExpTimeDur = Duration.between(food.getCreateDate(), LocalDateTime.now());
        long expirationDate = Math.abs(expirationDateDur.toDays());
        long residualExpTime = Math.abs(residualExpTimeDur.toDays());
        long percent = (residualExpTime / expirationDate) * 100;
        if (percent < 25) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean addFood(Food food) {
        boolean result = false;
        if (accept(food)) {
            result = warehouseStore.add(food);
        }
        return result;
    }

}

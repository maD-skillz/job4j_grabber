package ru.job4j.design.lsp;


import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Distributor {

    private final List<Food> warehouseStore = new ArrayList<>();

    @Override
    public List<Food> getCopyOfStore() {
        return new ArrayList<>(warehouseStore);
    }

    @Override
    public void clearStorage() {
        warehouseStore.clear();
    }

    @Override
    public boolean accept(Food food) {
        boolean result = false;
        if (percentGet(food) >= 0 && percentGet(food) < 25) {
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

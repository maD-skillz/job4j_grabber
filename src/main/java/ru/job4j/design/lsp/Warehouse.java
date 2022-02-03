package ru.job4j.design.lsp;


import java.util.ArrayList;
import java.util.List;

public class Warehouse extends Distributor {

    private final List<Food> warehouseStore = new ArrayList<>();

    @Override
    public void addFood(Food food) {
        warehouseStore.add(food);
    }

    @Override
    public Food getFood(Food food) {
        Food res = null;
        for (Food ind : warehouseStore) {
            if (ind.getName().equals(food.getName())) {
                res = food;
            }
        }
        return res;
    }
}

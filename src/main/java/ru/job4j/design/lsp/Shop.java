package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class Shop extends Distributor {

    private final List<Food> shopStore = new ArrayList<>();

    @Override
    public void addFood(Food food) {
        shopStore.add(food);
    }

    @Override
    public Food getFood(Food food) {
        Food res = null;
        for (Food ind : shopStore) {
            if (ind.getName().equals(food.getName())) {
                res = ind;
            }
        }
        return res;
    }
}

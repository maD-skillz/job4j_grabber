package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class Trash extends Distributor {

    private final List<Food> trashStore = new ArrayList<>();

    @Override
    public void addFood(Food food) {
        trashStore.add(food);
    }

    @Override
    public Food getFood(Food food) {
        Food res = null;
        for (Food ind : trashStore) {
            if (ind.getName().equals(food.getName())) {
               res = ind;
            }
        }
        return res;
    }
}

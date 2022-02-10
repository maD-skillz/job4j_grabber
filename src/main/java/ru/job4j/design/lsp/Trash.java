package ru.job4j.design.lsp;


import java.util.ArrayList;
import java.util.List;

public class Trash implements Distributor {

    private final List<Food> trashStore = new ArrayList<>();

    public List<Food> getTrashStore() {
        return trashStore;
    }

    @Override
    public boolean accept(Food food) {
        boolean result = false;
        if (percentGet(food) >= 100) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean addFood(Food food) {
        boolean result = false;
        if (accept(food)) {
            result = trashStore.add(food);
        }
        return result;
    }

}


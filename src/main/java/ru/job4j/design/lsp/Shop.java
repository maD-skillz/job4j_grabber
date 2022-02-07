package ru.job4j.design.lsp;


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
        if (percent(food) >= 25 && percent(food) <= 75) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean addFood(Food food) {
        boolean result = false;
        if (accept(food)) {
            if (percent(food) > 75) {
                food.setPrice(food.getPrice() - food.getDiscount());
            }
            result = shopStore.add(food);
        }
        return result;
    }
}

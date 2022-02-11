package ru.job4j.design.lsp;


import java.util.ArrayList;
import java.util.List;

public class Shop implements Distributor {

    private final List<Food> shopStore = new ArrayList<>();

    @Override
    public List<Food> getCopyOfStore() {
        return new ArrayList<>(shopStore);
    }

    @Override
    public boolean accept(Food food) {
        boolean result = false;
        if (percentGet(food) >= 25 && percentGet(food) <= 100) {
            result = true;
        }
        return result;
    }



    @Override
    public boolean addFood(Food food) {
        boolean result = false;
        if (accept(food)) {
            if (percentGet(food) >= 25 && percentGet(food) <= 75) {
                result = shopStore.add(food);
            }
            if (percentGet(food) > 75 && percentGet(food) < 100) {
                    food.setPrice(food.getPrice() - food.getDiscount());
                    result = shopStore.add(food);
                }
            }

        return result;
    }

}

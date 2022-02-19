package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {

    private List<Distributor> distributorList;

    public ControlQuality(List<Distributor> distributorList) {
        this.distributorList = distributorList;
    }

    public void distribution(Food food) {
        for (Distributor index : distributorList) {
            if (index.accept(food)) {
                index.addFood(food);
                break;
            }
        }
    }

    public List<Food> getList(List<Distributor> distributorList) {
        List<Food> list = new ArrayList<>();
        for (Distributor index : distributorList) {
            list = index.getCopyOfStore();
        }
        return list;
    }

    public Food getFoodFromL(List<Food> list) {
        Food resortFood = null;
        for (Food foodInd : list) {
            resortFood = foodInd;
        }
        return resortFood;
    }

    public void resort() {
        List<Food> foodList = getList(distributorList);
        Food resortFood = getFoodFromL(foodList);
        for (Distributor index : distributorList) {
            if (index.accept(resortFood)) {
                index.addFood(resortFood);
                break;
            }
        }
    }
}

package ru.job4j.design.lsp;

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
}

package ru.job4j.design.lsp;


import java.time.Duration;
import java.time.LocalDateTime;
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
        Duration expirationDateDur = Duration.between(food.getExpiryDate(), food.getCreateDate());
        Duration residualExpTimeDur = Duration.between(LocalDateTime.now(), food.getCreateDate());
        long expirationDate = Math.abs(expirationDateDur.toDays());
        long residualExpTime = Math.abs(residualExpTimeDur.toDays());
        if ((expirationDate - residualExpTime) <= 0) {
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


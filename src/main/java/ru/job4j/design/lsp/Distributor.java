package ru.job4j.design.lsp;


import java.time.Duration;
import java.time.LocalDateTime;

public interface Distributor {

    default double percentGet(Food food) {
        Duration expirationDateDur = Duration.between(food.getExpiryDate(), food.getCreateDate());
        Duration residualExpTimeDur = Duration.between(LocalDateTime.now(), food.getCreateDate());
        long expirationDate = Math.abs(expirationDateDur.toDays());
        long residualExpTime = Math.abs(residualExpTimeDur.toDays());
        return (double) (residualExpTime / expirationDate) * 100;
    }

    boolean accept(Food food);

    boolean addFood(Food food);
}



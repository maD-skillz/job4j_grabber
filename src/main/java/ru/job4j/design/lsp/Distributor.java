package ru.job4j.design.lsp;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public interface Distributor {

    List<Food> getCopyOfStore();

    default double percentGet(Food food) {
        double expirationDateDur = Duration.between(food.getExpiryDate(), food.getCreateDate()).toDays();
        double residualExpTimeDur = Duration.between(LocalDateTime.now(), food.getCreateDate()).toDays();
        return (residualExpTimeDur / expirationDateDur) * 100;
    }

    boolean accept(Food food);

    boolean addFood(Food food);
}



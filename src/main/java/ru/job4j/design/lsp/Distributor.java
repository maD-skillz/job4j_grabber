package ru.job4j.design.lsp;


import java.time.Duration;

public interface Distributor {

    default double discount(Food food) {
        long expirationDate = Duration.between(food.getCreateDate(), food.getExpiryDate()).toDays();
        if (food.getDiscount() != 0 && expirationDate != 0 && expirationDate > expirationDate * 100 / 75) {
            return food.getPrice() - food.getDiscount();
        }
        return 0;
    }

    boolean accept(Food food);

    boolean addFood(Food food);
}



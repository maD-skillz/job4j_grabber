package ru.job4j.design.lsp;

import java.time.LocalDateTime;

public class Apple extends Food {

    public Apple(String name, LocalDateTime expiryDate, LocalDateTime createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}

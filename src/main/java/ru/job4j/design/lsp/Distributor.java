package ru.job4j.design.lsp;



public abstract class Distributor {


    public void addFood(Food food) {

    }

    public Food getFood(Food food) {
        return new Food();
    }

}

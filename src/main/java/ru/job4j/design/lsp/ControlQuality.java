package ru.job4j.design.lsp;

public class ControlQuality {

    private int discount;

    public void distribution(Food food) {
        Distributor warehouse = new Warehouse();
        Distributor shop = new Shop();
        Distributor trash = new Trash();

        long expirationDate = food.getExpiryDate().getTimeInMillis() - food.getCreateDate().getTimeInMillis();

        if (expirationDate <= expirationDate / 4) {
            warehouse.addFood(food);

        } else if (expirationDate >= expirationDate / 4
        && expirationDate <= expirationDate * 100 / 75) {
            shop.addFood(food);

        } else if (expirationDate != 0 && expirationDate <= expirationDate * 100 / 75) {
            food.setDiscount(discount);
            shop.addFood(food);

        } else if (expirationDate <= 0) {
            trash.addFood(food);
        }
    }
}

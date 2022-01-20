package ru.job4j.tdd;

public class Ticket3D implements Ticket {

    int row;

    int column;

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }
}

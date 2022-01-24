package ru.job4j.solid.srp;

public class SingletonSRP {

    private static SingletonSRP instance = new SingletonSRP();

    private SingletonSRP() {

    }

    public static SingletonSRP getInstance() {
        return instance;
    }
}

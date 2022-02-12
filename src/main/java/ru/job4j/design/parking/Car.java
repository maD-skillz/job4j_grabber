package ru.job4j.design.parking;

import java.util.Objects;

public class Car implements Vehicle {

    private String model;

    private final int size = 1;

    public Car(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Car)) {
            return false;
        }
        Car car = (Car) o;
        return size == car.size && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, size);
    }
}

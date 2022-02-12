package ru.job4j.design.parking;

import java.util.Objects;

public class Truck implements Vehicle {

    private String model;

    private int size;

    public Truck(String model, int size) {
        this.model = model;
        this.size = size;
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

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Truck)) {
            return false;
        }
        Truck truck = (Truck) o;
        return size == truck.size && Objects.equals(model, truck.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, size);
    }
}

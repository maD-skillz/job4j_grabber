package ru.job4j.design.parking;

import java.util.ArrayList;

public class Parking implements ParkService {


    private final ArrayList<Vehicle> parkingSize = new ArrayList<>();

    @Override
    public boolean addVehicle(Vehicle vehicle) {
        return false;
    }

    @Override
    public boolean isFree() {
        return false;
    }
}

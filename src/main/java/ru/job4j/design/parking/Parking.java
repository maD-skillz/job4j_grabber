package ru.job4j.design.parking;

import java.util.ArrayList;
import java.util.List;

public class Parking implements ParkService {

    private int carPlace;

    private int truckPlace;

    private final List<Vehicle> parkingSize = new ArrayList<>();

    public Parking(int carPlace, int truckPlace) {
        this.carPlace = carPlace;
        this.truckPlace = truckPlace;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public boolean isCarOrTruck(Vehicle vehicle) {
        return false;
    }

    @Override
    public boolean addVehicle(Vehicle vehicle) {
        return false;
    }


}

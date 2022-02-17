package ru.job4j.design.parking;

import java.util.ArrayList;
import java.util.List;

public class Parking implements ParkService {

    private final int carPlace;

    private final int truckPlace;

    private int currentTruckSize = 0;

    private int currentCarSize = 0;

    private final List<Vehicle> parkingSize = new ArrayList<>();

    public Parking(int carPlace, int truckPlace) {
        this.carPlace = carPlace;
        this.truckPlace = truckPlace;
    }


    @Override
    public boolean addVehicle(Vehicle vehicle) {
        boolean result = false;
        if (carPlace != 0 | truckPlace != 0) {
            if (vehicle.getSize() > Car.SIZE && currentTruckSize + 1 <= truckPlace) {
                result = parkingSize.add(vehicle);
                currentTruckSize++;
            } else if (vehicle.getSize() > Car.SIZE && vehicle.getSize() <= carPlace && currentTruckSize + vehicle.getSize() <= carPlace) {
                result = parkingSize.add(vehicle);
                currentTruckSize += vehicle.getSize();
            } else if (vehicle.getSize() == Car.SIZE && currentCarSize + 1 <= carPlace) {
                result = parkingSize.add(vehicle);
                currentCarSize++;
            }
        }
        return result;
    }

}

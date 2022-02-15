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
    public boolean isValidPlace() {
        return carPlace != 0 | truckPlace != 0;
    }


    @Override
    public boolean addVehicle(Vehicle vehicle) {
        boolean result = false;
        if (isValidPlace()) {
            if (vehicle.getSize() > Car.SIZE && vehicle.getSize() <= carPlace) {
            currentTruckSize += vehicle.getSize();
            if (currentTruckSize <= carPlace) {
                result = parkingSize.add(vehicle);
            }
        } else if (vehicle.getSize() > Car.SIZE) {
            currentTruckSize++;
                if (currentTruckSize <= truckPlace) {
                    result = parkingSize.add(vehicle);
                }
            }

        }
        if (isValidPlace()) {
            if (vehicle.getSize() == Car.SIZE) {
                currentCarSize++;
                if (currentCarSize <= carPlace) {
                    result = parkingSize.add(vehicle);
                }
            }
        }
        return result;
    }


}

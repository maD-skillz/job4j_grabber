package ru.job4j.design.parking;

public interface ParkService {

    boolean isFree();

    boolean isCarOrTruck(Vehicle vehicle);

    boolean addVehicle(Vehicle vehicle);

}

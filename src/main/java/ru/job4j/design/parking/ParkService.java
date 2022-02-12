package ru.job4j.design.parking;

public interface ParkService {

    boolean addVehicle(Vehicle vehicle);

    boolean isFree();
}

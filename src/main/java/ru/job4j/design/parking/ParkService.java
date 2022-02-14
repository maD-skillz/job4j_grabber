package ru.job4j.design.parking;

public interface ParkService {

    boolean isFree();

    boolean addVehicle(Vehicle vehicle);

}

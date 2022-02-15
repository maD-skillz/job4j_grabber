package ru.job4j.design.parking;

public interface ParkService {

    boolean isValidPlace();

    boolean addVehicle(Vehicle vehicle);

}

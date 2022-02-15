package ru.job4j.design.parking;


import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingTest {

    @Test
    public void whenAddOneCarAndTruck() {
        Parking parking = new Parking(1, 1);
        Car car = new Car("Tesla");
        Truck truck = new Truck("Freightliner", 2);
        assertTrue(parking.addVehicle(car));
        assertTrue(parking.addVehicle(truck));
    }

    @Test
    public void whenAddTwoCarsAndTruck() {
        Parking parking = new Parking(2, 1);
        Car car1 = new Car("Tesla");
        Car car2 = new Car("Camry");
        Truck truck = new Truck("Volvo", 2);
        assertTrue(parking.addVehicle(car1));
        assertTrue(parking.addVehicle(car2));
        assertTrue(parking.addVehicle(truck));
    }

    @Test
    public void whenAddTwoTrucks() {
        Parking parking = new Parking(0, 2);
        Truck truck1 = new Truck("Freightliner", 4);
        Truck truck2 = new Truck("Volvo", 3);
        assertTrue(parking.addVehicle(truck1));
        assertTrue(parking.addVehicle(truck2));
    }

    @Test
    public void whenNoEmptyPlaces() {
        Parking parking = new Parking(0, 0);
        Car car1 = new Car("Tesla");
        Truck truck2 = new Truck("Volvo", 3);
        assertFalse(parking.addVehicle(car1));
        assertFalse(parking.addVehicle(truck2));
    }


    @Test
    public void whenCarOnTruckPlace() {
        Parking parking = new Parking(0, 1);
        Car car = new Car("Tesla");
        parking.addVehicle(car);
        assertFalse(parking.addVehicle(car));
    }


    @Test
    public void whenTruckOnCarPlace() {
        Parking parking = new Parking(1, 0);
        Truck truck = new Truck("Volvo", 3);
        parking.addVehicle(truck);
        assertFalse(parking.addVehicle(truck));
    }

    @Test
    public void whenThreeTrucksOnCarPlaceAndNotEnoughSize() {
        Parking parking = new Parking(6, 0);
        Truck truck1 = new Truck("Kamaz", 2);
        Truck truck2 = new Truck("Maz", 3);
        Truck truck3 = new Truck("Belaz", 5);
        assertTrue(parking.addVehicle(truck1));
        assertTrue(parking.addVehicle(truck2));
        assertFalse(parking.addVehicle(truck3));
    }

    @Test
    public void whenNotEnoughPlaceForThreeCarsAndTwoTrucks() {
        Parking parking = new Parking(2, 1);
        Car car1 = new Car("Tesla");
        Car car2 = new Car("Camry");
        Car car3 = new Car("Vesta");
        Truck truck1 = new Truck("Volvo", 2);
        Truck truck2 = new Truck("Maz", 3);
        assertTrue(parking.addVehicle(car1));
        assertTrue(parking.addVehicle(car2));
        assertTrue(parking.addVehicle(truck1));
        assertFalse(parking.addVehicle(car3));
        assertFalse(parking.addVehicle(truck2));
    }

}
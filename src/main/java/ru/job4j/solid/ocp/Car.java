package ru.job4j.solid.ocp;

public class Car {

    private class CarEngine {
        public String engine() {
            return "gasoline";
        }
    }

    private class CarBody {
        public String body() {
            return "sedan";
        }
    }

    private class WheelDrive {
        public String drive() {
            return "rear";
        }
    }
}

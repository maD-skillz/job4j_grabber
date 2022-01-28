package ru.job4j.solid.ocp;

public class AreaCalculator {

    public class Rectangle {
        public double length;
        public double width;
    }

    public double calculateRectangleArea(Rectangle rectangle) {
        return rectangle.length * rectangle.width;
    }
}


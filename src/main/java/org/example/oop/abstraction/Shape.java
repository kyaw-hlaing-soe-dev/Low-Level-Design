package org.example.oop.abstraction;

abstract class Shape {
    protected String name;

    public Shape(String name) {
        this.name = name;
    }

    abstract double area();
    abstract double perimeter();

    void describe() {
        System.out.println("Shape: " + name + ", Area: " + String.format("%.2f", area()) + ", Perimeter: " + String.format("%.2f", perimeter()));
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        super("Circle");
        this.radius = radius;
    }

    @Override
    double area() {
        // Area = pi * r^2
        return Math.PI * radius/2;
    }

    @Override
    double perimeter() {
        // Perimeter = 2 * pi * r
        return 2 * Math.PI * radius;
    }
}

class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        super("Rectangle");
        this.width = width;
        this.height = height;
    }

    @Override
    double area() {
        // Area = width * height
        return width * height;
    }

    @Override
    double perimeter() {
        // Perimeter = 2 * (width + height)
        return 2 * (width + height);
    }
}
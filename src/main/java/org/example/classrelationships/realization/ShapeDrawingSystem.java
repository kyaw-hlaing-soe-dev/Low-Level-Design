package org.example.classrelationships.realization;
import java.util.List;

interface Drawable {
    void draw();
    double getArea();
}

class Circle implements Drawable {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public void draw() {
        System.out.println("Drawing circle with radius " + radius);
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle implements Drawable {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public void draw() {
        System.out.println("Drawing rectangle " + width + "x" + height);
    }

    public double getArea() {
        return width * height;
    }
}

class Triangle implements Drawable {
    private double base;
    private double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    public void draw() {
        System.out.println("Drawing triangle with base " + base + " and height " + height);
    }

    public double getArea() {
        return 0.5 * base * height;
    }
}

class Canvas {
    public void drawAll(List<Drawable> shapes) {
        for (Drawable shape : shapes) {
            shape.draw();
            System.out.printf("  Area: %.2f%n%n", shape.getArea());
        }
    }
}

public class ShapeDrawingSystem {
    public static void main(String[] args) {
        Canvas canvas = new Canvas();

        List<Drawable> shapes = List.of(
                new Circle(5.0),
                new Rectangle(4.0, 6.0),
                new Triangle(3.0, 8.0)
        );

        canvas.drawAll(shapes);
    }
}
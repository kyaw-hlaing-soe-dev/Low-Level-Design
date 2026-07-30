package org.example.oop.enums;

public enum TrafficLight {
    RED(30),
    YELLOW(5),
    GREEN(25);

    private final int duration;

    TrafficLight(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public TrafficLight next() {
        switch (this) {
            case RED:
                return GREEN;
            case GREEN:
                return YELLOW;
            case YELLOW:
                return RED;
            default:
                return this;
        }
    }

    public void display() {
        System.out.println(this.name() + " (" + duration + "s)");
    }
}

package org.example.oop.enums;

public class TrafficLightApp {
    public static void main(String[] args){

        TrafficLight light = TrafficLight.RED;

        for (int i = 0; i < 6; i++) {
            light.display();
            light = light.next();
        }

    }
}

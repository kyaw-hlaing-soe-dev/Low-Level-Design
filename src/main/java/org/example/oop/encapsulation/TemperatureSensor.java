package org.example.oop.encapsulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TemperatureSensor {
    private List<Double> readings = new ArrayList<>();

    public void addReading(double value) {

        if(value >= -50 && value <= 150){
            readings.add(value);
        }
    }

    public double getAverage() {
        // Return the average of all readings, or 0.0 if no readings exist
        if(readings.isEmpty()){
            return 0.0;
        }
        double sum = 0.0;
        for(double reading : readings) {
            sum += reading;
        }
        return Math.round(sum / readings.size() * 100.0) / 100.0;
    }

    public int getReadingCount() {
        return readings.size();
    }

    public List<Double> getReadings() {
        return new ArrayList<>(readings);
    }
}



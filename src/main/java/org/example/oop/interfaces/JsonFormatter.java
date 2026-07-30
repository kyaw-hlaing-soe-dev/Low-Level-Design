package org.example.oop.interfaces;

public class JsonFormatter implements Formatter{

    @Override
    public String format(String message) {
        return "{\"message\": \"" + message + "\"}";
    }
}

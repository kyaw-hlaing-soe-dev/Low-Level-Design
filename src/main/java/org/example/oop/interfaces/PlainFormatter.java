package org.example.oop.interfaces;

public class PlainFormatter implements Formatter{


    @Override
    public String format(String message) {
        return "the message is: " + message;
    }
}

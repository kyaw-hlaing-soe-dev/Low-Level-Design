package org.example.oop.interfaces;

public class Logger {
    private Formatter formatter;

    public Logger(Formatter formatter){
        this.formatter = formatter;
    }

    public void log(String message) {
        System.out.println(formatter.format(message));
    }
}

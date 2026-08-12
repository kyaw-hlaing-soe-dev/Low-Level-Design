package org.example.oop.polymorphism;

import java.util.List;

interface Logger {
    void log(String level, String message);
    String getDestination();
}

class ConsoleLogger implements Logger {
    public void log(String level, String message) {
        System.out.println("[" + level + "] " + message);
    }

    public String getDestination() {
        return "Console";
    }
}

class FileLogger implements Logger {
    private String filePath;

    public FileLogger(String filePath) {
        this.filePath = filePath;
    }

    public void log(String level, String message) {
        System.out.println("Writing to " + filePath + ": [" + level + "] " + message);
    }

    public String getDestination() {
        return "File: " + filePath;
    }
}

class DatabaseLogger implements Logger {
    private String tableName;

    public DatabaseLogger(String tableName) {
        this.tableName = tableName;
    }

    public void log(String level, String message) {
        System.out.println("INSERT INTO " + tableName + ": [" + level + "] " + message);
    }

    public String getDestination() {
        return "Database: " + tableName;
    }
}

class Application {
    private Logger logger;

    public Application(Logger logger) {
        this.logger = logger;
    }

    public void run() {
        logger.log("INFO", "Application starting...");
        logger.log("INFO", "Processing data...");
        logger.log("INFO", "Application shutting down.");
    }
}


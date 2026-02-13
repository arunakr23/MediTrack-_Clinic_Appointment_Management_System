package com.airtribe.meditrack.util;

// Singleton class for generating unique IDs
public class IdGenerator {
    private static final IdGenerator INSTANCE;

    static{
        INSTANCE = new IdGenerator();
    }

    private int counter = 1000;

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        return INSTANCE;
    }

    // Synchronized to make ID generation thread-safe
    public synchronized int generateID() {
        return counter++;
    }
}

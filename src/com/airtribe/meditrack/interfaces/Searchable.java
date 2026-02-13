package com.airtribe.meditrack.interfaces;

public interface Searchable<T>{
    T searchById(int id);

    default void printSearchInfo() {
        System.out.println("Searching entity in the system...");
    }
}

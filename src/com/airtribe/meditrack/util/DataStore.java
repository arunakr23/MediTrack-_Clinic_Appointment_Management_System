package com.airtribe.meditrack.util;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.Collections;
import java.util.Map;

// Generic in-memory data store
public class DataStore<T> {

    // Using ConcurrentHashMap for thread safety
    private final Map<Integer, T> store = new ConcurrentHashMap<>();

    //saves an object with the given ID
    public void save(int id, T value) {
        store.put(id, value);
    }

    //retrieves an object by its ID
    public T get(int id) {
        return store.get(id);
    }

    //returns all stored objects
    public List<T> getAll() {
        return Collections.unmodifiableList(new ArrayList<>(store.values()));
    }

    //deletes an object by its ID
    public void delete(int id) {
        store.remove(id);
    }
}

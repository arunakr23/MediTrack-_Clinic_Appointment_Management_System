package com.airtribe.meditrack.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Generic in-memory data store
public class DataStore<T> {

    private final Map<Integer, T> store = new HashMap<>();

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
        return new ArrayList<>(store.values());
    }

    //deletes an object by its ID
    public void delete(int id) {
        store.remove(id);
    }
}

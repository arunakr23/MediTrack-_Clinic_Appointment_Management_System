package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.util.Validator;

public abstract class Person extends MedicalEntity {
    
    private String name;
    private int age;

    public Person(int id, String name, int age) throws InvalidDataException {
        
        // Validate inputs
        Validator.validateName(name);
        Validator.validateAge(age);

        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getters 
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
        
}


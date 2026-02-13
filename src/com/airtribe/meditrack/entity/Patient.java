package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.exception.InvalidDataException;
public class Patient extends Person implements Cloneable {

    private String illness;
    
    public Patient(int id, String name, int age, String illness) throws InvalidDataException {
  
        super(id, name, age);
        this.illness = illness;
    }

    // Getters
    
    public String getIllness() {
        return illness;
    }

    @Override
    public Patient clone() {
        try {
            return (Patient) super.clone(); // Shallow copy
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning failed");
        }
    }

    @Override
    public String toString() {
        return "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", illness='" + illness + '\'';
    }
    
}

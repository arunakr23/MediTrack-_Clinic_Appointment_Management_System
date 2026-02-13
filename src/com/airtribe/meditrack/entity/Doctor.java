package com.airtribe.meditrack.entity;
import com.airtribe.meditrack.enums.Specialization;
import com.airtribe.meditrack.exception.InvalidDataException;
public class Doctor extends Person {

    private Specialization specialization;
    private double consultationFee;

    public Doctor(int id, String name, int age, Specialization specialization, double consultationFee) throws InvalidDataException {

        //call parent constructor
        super(id, name, age);
        this.specialization = specialization;
        this.consultationFee = consultationFee;
    }
    
    // Getters
    public Specialization getSpecialization() {
        return specialization;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    @Override
    public String toString() {
        return 
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", specialization=" + specialization +
                ", consultationFee=" + consultationFee ;
    }
}

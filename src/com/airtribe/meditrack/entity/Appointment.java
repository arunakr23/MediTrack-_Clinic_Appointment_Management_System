package com.airtribe.meditrack.entity;

import java.time.LocalDate;

import com.airtribe.meditrack.enums.AppointmentStatus;
public class Appointment extends MedicalEntity implements Cloneable {
    
    private Doctor doctor;
    private Patient patient;
    private LocalDate appointmentDate;
    private AppointmentStatus status;

    public Appointment(int id, Doctor doctor, Patient patient, LocalDate appointmentDate) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient.clone(); //deep copy of patient to avoid shared reference issues
        this.appointmentDate = appointmentDate;
        this.status = AppointmentStatus.PENDING;
    }

    // Getters
    public Doctor getDoctor() {
        return doctor;
    }   

    public Patient getPatient() {
        return patient;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void confirm() {
    this.status = AppointmentStatus.CONFIRMED;
    }

    public void cancel() {
        this.status = AppointmentStatus.CANCELLED;
    }

     
    @Override
    public Appointment clone() { 
        try {
            Appointment copy = (Appointment) super.clone(); // Shallow copy
            copy.patient = this.patient.clone(); // Deep copy of Patient
            return copy; 
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Appointment cloning failed");
        }
    }
}

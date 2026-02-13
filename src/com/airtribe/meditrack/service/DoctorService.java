package com.airtribe.meditrack.service;

import java.util.List;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.enums.Specialization;
import com.airtribe.meditrack.util.DataStore;
public class DoctorService {
    
    private DataStore<Doctor> store = new DataStore<>();

    //adding a doctor to the data store
    public void addDoctor(Doctor doctor) {
        store.save(doctor.getId(), doctor);
    }

    //retrieving a doctor by ID
    public Doctor getDoctor(int id) {
        return store.get(id);
    }

    //retrieving all doctors
    public List<Doctor> getAllDoctors() {
        return store.getAll();
    }

    //deleting a doctor by ID
    public void deleteDoctor(int id) {
        store.delete(id);
    }

    //using streams to filter doctors by specialization
    public List<Doctor> filterBySpecialization(Specialization specialization) {
        return store.getAll().stream()
            .filter(doc -> doc.getSpecialization() == specialization)
            .toList();
    }
  
    //using streams to calculate average consultation fee
    public double averageFee() {
        return store.getAll().stream()
            .mapToDouble(Doctor::getConsultationFee)
            .average()
            .orElse(0.0);
    }

    //using streams to search doctors by name
    public List<Doctor> searchDoctorsByName(String name) {
        return store.getAll().stream()
            .filter(doctor -> doctor.getName().toLowerCase().contains(name.toLowerCase()))
            .toList();
    }
}

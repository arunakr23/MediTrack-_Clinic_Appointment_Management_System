package com.airtribe.meditrack.service;

import java.util.List;

import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.interfaces.Searchable;
import com.airtribe.meditrack.util.DataStore;
public class PatientService implements Searchable<Patient> {
  
    private DataStore<Patient> patientStore = new DataStore<>();

    //adding a patient to the data store
    public void addPatient(Patient patient) {
        patientStore.save(patient.getId(), patient);
    }

    //retrieving a patient by ID
    public Patient getPatient(int id) {
        return patientStore.get(id);
    }

    @Override
    public Patient searchById(int id) {
        return getPatient(id);
    }

    //retrieving all patients
    public List<Patient> getAllPatients() {
        return patientStore.getAll();
    }

    //deleting a patient by ID
    public void deletePatient(int id) {
        patientStore.delete(id);
    }

    //using streams to search patients by name
    public List<Patient> searchPatientsByName(String name) {
        return patientStore.getAll().stream()
            .filter(patient -> patient.getName().toLowerCase().contains(name.toLowerCase()))
            .toList();
    }

   //using streams to search patients by age
     public List<Patient> searchPatientsByAge(int age) {
        return patientStore.getAll().stream()
            .filter(patient -> patient.getAge() == age)
            .toList();
    }
}

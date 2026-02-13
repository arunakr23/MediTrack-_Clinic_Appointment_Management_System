package com.airtribe.meditrack.test;

import java.time.LocalDate;
import java.util.List;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.BillSummary;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.enums.AppointmentStatus;
import com.airtribe.meditrack.enums.Specialization;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;
import com.airtribe.meditrack.util.IdGenerator;
public class TestRunner {
    public static void main(String[] args) {
        System.out.println("MediTrack - Simple Test Runner");
        System.out.println("--------------------------------");

        DoctorService doctorService = new DoctorService();
        PatientService patientService = new PatientService();
        AppointmentService appointmentService = new AppointmentService();
        IdGenerator idGenerator = IdGenerator.getInstance();

        try {
            //Create doctor and patient and add to service
            Doctor doctor = new Doctor(idGenerator.generateID(), "Dr. Shruthi Reddy", 45, Specialization.CARDIOLOGY, 800.0);
            doctorService.addDoctor(doctor);
            System.out.println("Doctor added: " + doctor.getName() + " (" + doctor.getSpecialization() + ")");

            Patient patient = new Patient(idGenerator.generateID(), "Rohan Gowda", 35, "Hypertension");
            patientService.addPatient(patient);
            System.out.println("Patient added: " + patient.getName());

            //Appointment creation and confirmation
            Appointment appointment = new Appointment(idGenerator.generateID(), doctor, patient, LocalDate.now());
            appointmentService.addAppointment(appointment);
            System.out.println("Appointment status (before): " + appointment.getStatus());
            appointmentService.confirmAppointment(appointment.getId());
            System.out.println("Appointment status (after): " + appointment.getStatus());

            //Cloning entities to test deep copy and immutability 
            Patient clonedPatient = patient.clone();
            System.out.println("Cloned patient name: " + clonedPatient.getName());
            Appointment clonedAppointment = appointment.clone();
            System.out.println("Cloned appointment status: " + clonedAppointment.getStatus());

            //Billing and immutability test with BillSummary
            Bill bill = new Bill(500.0);
            double total = bill.calculateAmount();
            BillSummary summary = new BillSummary(101, total); // immutable summary
            System.out.println("Bill total (with tax): " + summary.getTotalAmount());

            //Streams searching for patients
            List<Patient> searchByName = patientService.searchPatientsByName("Rohan");
            System.out.println("Search by name 'Rohan': " + searchByName.size());
            List<Patient> searchByAge = patientService.searchPatientsByAge(35);
            System.out.println("Search by age 35: " + searchByAge.size());

            //Exception handling 
            try {
                appointmentService.getAppointment(99999);
                System.out.println("Expected AppointmentNotFoundException did NOT happen");
            } catch (AppointmentNotFoundException e) {
                System.out.println("AppointmentNotFoundException caught (ok)");
            }

            try {
                new Patient(idGenerator.generateID(), "John123", 30, "Fever");
                System.out.println("Expected InvalidDataException did NOT happen");
            } catch (InvalidDataException e) {
                System.out.println("InvalidDataException caught (ok)");
            }

            //Testing enum usage
            AppointmentStatus status = appointment.getStatus();
            System.out.println("Final appointment status enum: " + status);

            System.out.println("\n All tests completed successfully!");
        } catch (Exception e) {
            System.out.println("Test runner failed: " + e.getMessage());
        }
    }
}

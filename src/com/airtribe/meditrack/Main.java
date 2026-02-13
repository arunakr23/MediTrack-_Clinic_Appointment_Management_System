package com.airtribe.meditrack;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.enums.Specialization;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;
import com.airtribe.meditrack.util.IdGenerator;
import com.airtribe.meditrack.util.Validator;
public class Main {
    public static void main(String[] args) {
       
        Scanner scanner = new Scanner(System.in);
        
        DoctorService doctorService = new DoctorService();
        PatientService patientService = new PatientService();
        AppointmentService appointmentService = new AppointmentService();

        IdGenerator idGenerator = IdGenerator.getInstance(); // Using the singleton ID generator

        while(true) {
            System.out.println("\n===== Welcome to MediTrack - Clinic & Appointment Management System =====");
            System.out.println("1. Add a Doctor");
            System.out.println("2. Add a Patient");
            System.out.println("3. Schedule an Appointment");
            System.out.println("4. View all Doctors");
            System.out.println("5. View all Patients");
            System.out.println("6. View all Appointments");
            System.out.println("7. Confirm an Appointment");
            System.out.println("8. Cancel an Appointment");
            System.out.println("9. Search Doctors by Name");
            System.out.println("10. Search Patients by Name");
            System.out.println("11. Delete a Patient");
            System.out.println("12. Delete a Doctor");
            System.out.println("13. Average Consultation Fee of Doctors");
            System.out.println("14. Generate Bill");
            System.out.println("0. Exit");

            System.out.println("Please enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            try {
                switch (choice) {
                    case 1: 
                        // Add Doctor
                        System.out.println("Enter Doctor Name: ");
                        String docName = scanner.nextLine();
                        
                        // Validate name before proceeding
                        try {
                            Validator.validateName(docName);
                        } catch (InvalidDataException e) {
                            System.out.println("Validation Error: " + e.getMessage());
                            break;
                        }

                        System.out.println("Enter Age: ");
                        int docAge = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Select Specialization: ");
                        for (Specialization spec : Specialization.values()) {
                            System.out.println(spec.ordinal() + 1 + ". " + spec);
                        }
                        int specChoice = scanner.nextInt();
                        scanner.nextLine();
                        Specialization specialization = Specialization.values()[specChoice - 1];

                        System.out.println("Enter Consultation Fee: ");
                        double fee = scanner.nextDouble();
                        scanner.nextLine();
                        
                        Doctor doctor = new Doctor(idGenerator.generateID(), docName, docAge, specialization, fee);
                        doctorService.addDoctor(doctor);

                        System.out.println("Doctor added successfully with ID: " + doctor.getId());
                        break;

                    case 2:
                        // Add Patient
                        System.out.println("Enter Patient Name: ");
                        String patientName = scanner.nextLine();
                        
                        // Validate name before proceeding
                        try {
                            Validator.validateName(patientName);
                        } catch (InvalidDataException e) {
                            System.out.println("Validation Error: " + e.getMessage());
                            break;
                        }

                        System.out.println("Enter Age: ");
                        int patientAge = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Enter Illness: ");
                        String illness = scanner.nextLine();

                        Patient patient = new Patient(idGenerator.generateID(), patientName, patientAge, illness);
                        patientService.addPatient(patient);

                        System.out.println("Patient added successfully with ID: " + patient.getId());
                        break;

                    case 3:
                        // Schedule Appointment
                        System.out.println("Enter Patient ID: ");
                        int patientId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Enter Doctor ID: ");
                        int doctorId = scanner.nextInt();
                        scanner.nextLine();

                        Doctor doc = doctorService.getDoctor(doctorId);
                        Patient pat = patientService.getPatient(patientId);

                        if( doc == null || pat == null) {
                            System.out.println("Invalid Doctor or Patient ID. Please try again!");
                            break;
                        } 

                        Appointment appointment = new Appointment(idGenerator.generateID(), doc, pat, LocalDate.now());
                        appointmentService.addAppointment(appointment);

                        System.out.println("Appointment scheduled successfully with ID: " + appointment.getId());
                        break;

                    case 4:
                        // View all Doctors
                        System.out.println("List of Doctors:");
                        List<Doctor> doctors = doctorService.getAllDoctors();
                        if (doctors.isEmpty()) {
                            System.out.println("No doctors found!");
                        } else {
                            doctors.forEach(System.out::println);
                        }
                        break;

                    case 5:
                        // View all Patients
                        System.out.println("List of Patients:");
                        List<Patient> patients = patientService.getAllPatients();
                        if (patients.isEmpty()) {
                            System.out.println("No patients found!");
                        } else {
                            patients.forEach(System.out::println);
                        }
                        break;

                    case 6:
                        // View all Appointments
                        System.out.println("List of Appointments:");
                        List<Appointment> appointments = appointmentService.getAllAppointments();
                        if (appointments.isEmpty()) {
                            System.out.println("No appointments found!");
                        } else {
                            for (Appointment apt : appointments) {
                                System.out.println("ID: " + apt.getId() + 
                                                   ", Patient: " + apt.getPatient().getName() + 
                                                   ", Doctor: " + apt.getDoctor().getName() + 
                                                   ", Date: " + apt.getAppointmentDate() + 
                                                   ", Status: " + apt.getStatus());
                            }
                        }
                        break;

                    case 7:
                        // Confirm Appointment
                        System.out.println("Enter Appointment ID to confirm: ");
                        int confirmId = scanner.nextInt();
                        appointmentService.confirmAppointment(confirmId);
                        System.out.println("Appointment confirmed successfully!");
                        break;

                    case 8:
                        // Cancel Appointment   
                        System.out.println("Enter Appointment ID to cancel: ");
                        int cancelId = scanner.nextInt();   
                        appointmentService.cancelAppointment(cancelId);
                        System.out.println("Appointment cancelled successfully!");
                        break;

                    case 9:
                        // Search Doctors by Name
                        System.out.println("Enter Doctor Name to search: ");
                        String searchDoctorName = scanner.nextLine();
                        System.out.println("Search Results:");
                        List<Doctor> doctorSearchResults = doctorService.searchDoctorsByName(searchDoctorName);
                        if (doctorSearchResults.isEmpty()) {
                            System.out.println("Doctor not found!");
                        } else {
                            doctorSearchResults.forEach(System.out::println);
                        }
                        break;

                    case 10:
                        // Search Patients by Name
                        System.out.println("Enter Patient Name to search: ");
                        String searchName = scanner.nextLine();
                        System.out.println("Search Results:");
                        List<Patient> searchResults = patientService.searchPatientsByName(searchName);
                        if (searchResults.isEmpty()) {
                            System.out.println("Patient not found!");
                        } else {
                            searchResults.forEach(System.out::println);
                        }
                        break;

                    case 11:
                        // Delete Patient
                        System.out.println("Enter Patient ID to delete: ");
                        int deletePatientId = scanner.nextInt();
                        scanner.nextLine();
                        
                        Patient patientToDelete = patientService.getPatient(deletePatientId);
                        if (patientToDelete == null) {
                            System.out.println("Patient not found with ID: " + deletePatientId);
                        } else {
                            patientService.deletePatient(deletePatientId);
                            System.out.println("Patient deleted successfully!");
                        }
                        break;

                    case 12:
                        // Delete Doctor
                        System.out.println("Enter Doctor ID to delete: ");
                        int deleteDoctorId = scanner.nextInt();
                        scanner.nextLine();
                        
                        Doctor doctorToDelete = doctorService.getDoctor(deleteDoctorId);
                        if (doctorToDelete == null) {
                            System.out.println("Doctor not found with ID: " + deleteDoctorId);
                        } else {
                            doctorService.deleteDoctor(deleteDoctorId);
                            System.out.println("Doctor deleted successfully!");
                        }
                        break;

                    case 13:
                        // Average Consultation Fee of Doctors
                        double averageFee = doctorService.averageFee();
                        System.out.println("Average Consultation Fee of Doctors: Rs." + averageFee);
                        break;

                    case 14:
                        // Generate Bill
                        System.out.println("Enter Appointment ID for billing: ");
                        int billAppointmentId = scanner.nextInt();
                        scanner.nextLine();
                        
                        Appointment billAppointment = appointmentService.getAppointment(billAppointmentId);
                        double consultationFee = billAppointment.getDoctor().getConsultationFee();
                        
                        Bill bill = new Bill(consultationFee);
                        double totalAmount = bill.calculateAmount();
                        
                        System.out.println("\n===== Bill Details =====");
                        System.out.println("Appointment ID: " + billAppointment.getId());
                        System.out.println("Patient: " + billAppointment.getPatient().getName());
                        System.out.println("Doctor: " + billAppointment.getDoctor().getName());
                        System.out.println("Base Consultation Fee: Rs." + consultationFee);
                        System.out.println("Tax (18%): Rs." + (consultationFee * 0.18));
                        System.out.println("Total Amount: Rs." + totalAmount);
                        System.out.println("========================\n");
                        break;

                    case 0:
                        System.out.println("Thank you for using MediTrack. Goodbye!");
                        scanner.close();
                        return;
                        
                    default:
                        System.out.println("Invalid choice. Please try again!");
                        
                }

            } catch (InvalidDataException e) {
                System.out.println("Validation Error: " + e.getMessage());

                } catch (AppointmentNotFoundException e) {
                    System.out.println("Error: " + e.getMessage());

                } catch (Exception e) {
                    System.out.println("An unexpected error occurred: " + e.getMessage());

                }
            }
            
        }
    }




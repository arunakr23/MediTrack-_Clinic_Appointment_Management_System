package com.airtribe.meditrack.service;

import java.time.LocalDate;
import java.util.List;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.enums.AppointmentStatus;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.exception.InvalidAppointmentStateException;
import com.airtribe.meditrack.util.DataStore;
public class AppointmentService {
    
    private DataStore<Appointment> appointmentDataStore = new DataStore<>();

    //adding appointment to the datastore
    public void addAppointment(Appointment appointment) {
        appointmentDataStore.save(appointment.getId(), appointment);
    }

    //retrieving an appointment by ID
    public Appointment getAppointment(int id) throws AppointmentNotFoundException {
        Appointment appointment = appointmentDataStore.get(id);
        if (appointment == null) {
            throw new AppointmentNotFoundException("Appointment with ID " + id + " not found.");
        }
        return appointment;
    }

    //retrieving all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentDataStore.getAll();
    }

    //confirming an appointment by ID - Update
    public void confirmAppointment(int id) throws AppointmentNotFoundException, InvalidAppointmentStateException {
        Appointment appointment = getAppointment(id);
        if (appointment.getStatus() == AppointmentStatus.CANCELLED) {
            throw new InvalidAppointmentStateException("Cannot confirm a cancelled appointment.");
        }
        appointment.confirm();
        appointmentDataStore.save(id, appointment);
    }

    //canceling an appointment by ID - Update
    public void cancelAppointment(int id) throws AppointmentNotFoundException {
        Appointment appointment = getAppointment(id);
        appointment.cancel();
        appointmentDataStore.save(id, appointment);
    }

    //deleting an appointment by ID
    public void deleteAppointment(int id) {
        appointmentDataStore.delete(id);
    }

    //using streams to filter appointments by doctor
    public List<Appointment> getAppointmentsByDoctor(Doctor doctor) {
        return appointmentDataStore.getAll().stream()
            .filter(app -> app.getDoctor().equals(doctor))
            .toList();
    }

    //using streams to filter appointments by date
    public List<Appointment> getAppointmentsByDate(LocalDate date) {
        return appointmentDataStore.getAll().stream()
            .filter(app -> app.getAppointmentDate().equals(date))
            .toList();
    }

    //using streams to count appointments for a specific doctor
    public long countAppointmentsByDoctor(Doctor doctor) {
        return appointmentDataStore.getAll().stream()
            .filter(app -> app.getDoctor().equals(doctor))
            .count();
    }
}

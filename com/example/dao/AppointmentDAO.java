//This class represents the data access object(dao) for managing appointments

package com.example.dao;

import com.example.model.Appointment;
import com.example.model.Doctor;
import com.example.model.Patient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.NotFoundException;
import jdk.vm.ci.meta.Local;

public class AppointmentDAO {
    private static List<Appointment> appointments = new ArrayList<>();

    // Static block for initializing sample data
    static {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        String formattedDate = date.format(dateFormatter);
        String formattedTime = time.format(timeFormatter);
        
        List<Patient> allPatients = PatientDAO.getAllPatients();
        // Get the doctor objects from DoctorDAO
        List<Doctor> allDoctors = DoctorDAO.getAllDoctors();

        // Create appointments and add them to the list
        appointments.add(new Appointment(1, formattedDate, formattedTime, allPatients.get(0), allDoctors.get(0)));
        appointments.add(new Appointment(2, formattedDate, formattedTime, allPatients.get(1), allDoctors.get(1)));
        
    }
        // Method to retrieve all appointments

     public List<Appointment> getAllAppointments() {
        return appointments;
    }
     
         // Method to retrieve an appointment by ID
      public Appointment getAppointmentById(int appointmentId) {
        
        for (Appointment appointment : appointments) {
            if (appointment.getId() == appointmentId) {
                return appointment;
            }
        }
        return null;
    }
        // Method to add a new appointment
    public void addAppointment(Appointment appointment) {
        int newUserId = getNextAppointmentId();
        appointment.setId(newUserId);
        appointments.add(appointment);
    }

   

   // Method to update an existing appointment
    public void updateAppointment(int appointmentId, Appointment updatedAppointment) throws NotFoundException {
        Appointment appointment = getAppointmentById(appointmentId);
        // Update appointment attributes
        appointment.setDate(updatedAppointment.getDate());
     
    }
      
          // Method to delete an appointment by ID

    public void deleteAppointment(int appointmentId) throws NotFoundException {
        appointments.removeIf(appoin -> appoin.getId() == appointmentId);
    }
        // Method to get the next available appointment ID
    public int getNextAppointmentId() {

        int maxUserId = Integer.MIN_VALUE;

        for (Appointment apointment : appointments) {
            int AppointmentId = apointment.getId();
            if (AppointmentId > maxUserId) {
                maxUserId = AppointmentId;
            }
        }
        return maxUserId + 1;
    }
}

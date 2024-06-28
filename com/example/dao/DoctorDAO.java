package com.example.dao;

import com.example.model.Doctor;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO extends PersonDAO {

    private static List<Doctor> doctors = new ArrayList<>();

    // Initialize some sample doctor records
    static {
        doctors.add(new Doctor(10, "John Smith", "123455", "pilana", "Radiologist"));
        doctors.add(new Doctor(11, "Michael Williams", "123455", "pilana", "Cardiologist"));
        doctors.add(new Doctor(12, "Emma Clarke", "123455", "pilana", "Neurologist"));
    }

    // Retrieve all doctors
    public static List<Doctor> getAllDoctors() {
        return doctors;
    }

    // Retrieve a doctor by their ID
    public Doctor getDoctorById(int id) throws NotFoundException {
        for (Doctor doctor : doctors) {
            if (doctor.getID() == id) {
                return doctor;
            }
        }
        throw new NotFoundException("Doctor not found with ID: " + id);
    }

    // Add a new doctor
    public void addDoctor(Doctor doctor) {
        int newDoctorId = getNextDoctorId();
        doctor.setID(newDoctorId);
        addPerson(doctor); // Add the person to the persons list
        doctors.add(doctor);
    }
    
    // Update a doctor's information
    public void updateDoctor(Doctor updatedStudent) {
        for (int i = 0; i < doctors.size(); i++) {
            Doctor doctor = doctors.get(i);
            if (doctor.getID() == updatedStudent.getID()) {
                doctors.set(i, updatedStudent);
                System.out.println("Doctor updated: " + updatedStudent);
                return;
            }
        }
    }
    
    // Delete a doctor by their ID
    public void deleteDoctor(int id) throws NotFoundException {
        doctors.removeIf(doctor1 -> doctor1.getID() == id);
    }
    
    // Get the next available doctor ID
    public int getNextDoctorId() {
        int maxDoctorId = Integer.MIN_VALUE;
        for (Doctor doctor : doctors) {
            int userId = doctor.getID();
            if (userId > maxDoctorId) {
                maxDoctorId = userId;
            }
        }
        return maxDoctorId + 1;
    }
}

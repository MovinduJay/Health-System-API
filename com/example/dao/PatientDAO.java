package com.example.dao;

import com.example.model.Patient;
import com.example.model.Person;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.NotFoundException;

public class PatientDAO extends PersonDAO {

    private static List<Patient> patients = new ArrayList<>();
    
    // Initialize some dummy data
    static {
        patients.add(new Patient(1, "Daniel Johnes", "0767845132", "Colombo", "Need to do a surgery", "good"));
        patients.add(new Patient(2, "Olivia Brown", "0775421456", "Gampaha", "Take an x ray", "intermediate"));
    }
     
    // Retrieve all patients
    public static List<Patient> getAllPatients() {
        return patients;
    }
    
    // Retrieve a patient by ID
    public Patient getPatientById(int id) {
        for (Patient patient : patients) {
            if (patient.getID() == id) {
                return patient;
            }
        }
        return null;
    }

    // Add a new patient
    public void addPatient(Patient patient) {
        int newpatientId = getNextPatientId();
        patient.setID(newpatientId);
        addPerson(patient);
        patients.add(patient);
    }

    // Update a patient
    public void updatePatient(Patient updatedPatient) {
        for (int i = 0; i < patients.size(); i++) {
            Patient patient = patients.get(i);
            if (patient.getID() == updatedPatient.getID()) {
                patients.set(i, updatedPatient);
                System.out.println("Patient updated: " + updatedPatient);
                return;
            }
        }
    }

    // Delete a patient
    public void deletePatient(int id) throws NotFoundException {
        patients.removeIf(patientt -> patientt.getID() == id);
    }
    
    // Get the next available patient ID
    public int getNextPatientId() {
        int maxpatientId = Integer.MIN_VALUE;
        for (Patient patient : patients) {
            int userId = patient.getID();
            if (userId > maxpatientId) {
                maxpatientId = userId;
            }
        }
        return maxpatientId + 1;
    }
}

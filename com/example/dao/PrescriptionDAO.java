package com.example.dao;

import com.example.model.Doctor;
import com.example.model.Patient;
import com.example.model.Prescription;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.NotFoundException;

public class PrescriptionDAO {
    private static List<Prescription> prescriptions = new ArrayList<>();
    
    // Initialize prescription data
    static {
        List<Patient> allPatients = PatientDAO.getAllPatients();
        List<Doctor> allDoctors = DoctorDAO.getAllDoctors();
        
        prescriptions.add(new Prescription(allPatients.get(0), allDoctors.get(0), "Paracetamol", 2, "Take one tablet twice a day", 6));
        prescriptions.add(new Prescription(allPatients.get(1), allDoctors.get(0), "Ibuprofen", 2, "Take one tablet three times a day after meals", 7));
    }
    
    // Get all prescriptions
    public List<Prescription> getAllPrescriptions() {
        return prescriptions;
    }
    
    // Get prescription by patient ID
    public Prescription getPrescription(int id) throws NotFoundException {
        for (Prescription prescription : prescriptions) {
            if (prescription.getPatient().getID() == id) {
                return prescription;
            }
        }
        return null;
    }

    // Add prescription
    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }

    // Update prescription
    public void updatePrescription(Prescription updatedPrescription) {
        for (int i = 0; i < prescriptions.size(); i++) {
            Prescription prescription = prescriptions.get(i);
            if (prescription.getPatient().getID() == updatedPrescription.getPatient().getID()) {
                prescriptions.set(i, updatedPrescription);
                System.out.println("Prescription updated: " + updatedPrescription);
                return;
            }
        }
    }

    // Delete prescription
    public void deletePrescription(int id) throws NotFoundException {
        Prescription prescription = getPrescription(id);
        prescriptions.remove(prescription);
    }
}

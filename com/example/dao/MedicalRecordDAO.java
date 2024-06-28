package com.example.dao;

import com.example.model.MedicalRecord;
import com.example.model.Patient;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDAO {
    private static List<MedicalRecord> medicalRecords = new ArrayList<>();
    static List<Patient> allRecords = PatientDAO.getAllPatients();
    
    // Initialize some dummy data
    static {
        medicalRecords.add(new MedicalRecord(allRecords.get(0).getID(), 1, "Diabetes", "Treatment 1", "Patient advised to follow a low-sodium diet and engage in regular exercise"));
        medicalRecords.add(new MedicalRecord(allRecords.get(0).getID(), 2, "Depression", "Treatment 2", "Patient referred to a psychiatrist for further evaluation and management"));
        medicalRecords.add(new MedicalRecord(allRecords.get(0).getID(), 3, "Appendicitis", "Treatment 3", "Patient recovered well post-surgery with no complications"));
    }
    
    // Retrieve all medical records
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecords;
    }
    
    // Retrieve a specific medical record by ID
    public MedicalRecord getMedicalRecord(int id) {
        for (MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getPatientId() == id) {
                return medicalRecord;  
            }
        }
        return null;
    }
    
    // Retrieve all medical records for a specific patient
    public List<MedicalRecord> getMedicalRecords(int id) {
        List<MedicalRecord> matchingRecords = new ArrayList<>();
        for (MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getPatientId() == id) {
                matchingRecords.add(medicalRecord);
            }
        }
        return matchingRecords;
    }
   
    // Add a new medical record
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecords.add(medicalRecord);
    }

    // Update a medical record
    public void updateMedicalRecord(int id, MedicalRecord updatedMedicalRecord) throws NotFoundException {
        MedicalRecord medicalRecord = getMedicalRecord(id);
        if (medicalRecord != null) {
            // Update the fields of the existing medical record with the updated values
            medicalRecord.setPatient(updatedMedicalRecord.getPatient());
            medicalRecord.setMedicalRecordId(updatedMedicalRecord.getMedicalRecordId());
            medicalRecord.setTreatments(updatedMedicalRecord.getTreatments());
            medicalRecord.setDiagnoses(updatedMedicalRecord.getDiagnoses());
            medicalRecord.setOthetData(updatedMedicalRecord.getOtherData());
        } else {
            throw new NotFoundException("Medical record with ID " + id + " not found");
        }
    }

    // Delete a medical record
    public void deleteMedicalRecord(int id) throws NotFoundException {
        MedicalRecord medicalRecord = getMedicalRecord(id);
        if (medicalRecord != null) {
            medicalRecords.remove(medicalRecord);
        } else {
            throw new NotFoundException("Medical record with ID " + id + " not found");
        }
    }
}

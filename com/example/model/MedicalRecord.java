
package com.example.model;


public class MedicalRecord {
    
   private int medicalRecordId;
   private int patient;
   private String diagnoses;
   private String treatments;
   private String otherData;
   
   public MedicalRecord(){
   }

    public MedicalRecord(int patient, int medicalRecordId,  String diagnoses, String treatment, String otherData) {
        this.medicalRecordId = medicalRecordId;
        this.patient = patient;
        this.diagnoses = diagnoses;
        this.treatments = treatment;
        this.otherData = otherData;
      
    }
//getters and setters
    public int getMedicalRecordId() {
        return medicalRecordId;
    }

    public void setMedicalRecordId(int medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }
    
    public int getPatient() {
        return patient;
    }

    public void setPatient(int patient) {
        this.patient = patient;
    }
    
    public int getPatientId() {
        return getPatient(); 
    }
    
    public String getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(String diagnoses) {
        this.diagnoses = diagnoses;
    }
    
    public String getTreatments() {
        return treatments;
    }

    public void setTreatments(String treatments) {
        this.treatments = treatments;
    }
    
    public String getOtherData() {
        return otherData;
    }

    public void setOthetData(String otherData) {
        this.otherData = otherData;
    }
    
}

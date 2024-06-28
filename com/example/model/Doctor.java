
package com.example.model;


public class Doctor extends Person{
    
    private String specialization;
    
      public Doctor() {
    }
    
    public Doctor(int id, String name, String contactInformation, String address, String specialization) {
        super(id, name, contactInformation, address);
        this.specialization = specialization;
      
    }
    //getters and setters
    
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}

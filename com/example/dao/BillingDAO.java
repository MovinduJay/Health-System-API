// This class represents the data access object (DAO) for managing billing records.

package com.example.dao;

import com.example.model.Billing;
import com.example.model.Patient;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.NotFoundException;

public class BillingDAO {
    private static List<Billing> billings = new ArrayList<>();
    
    // Static block for initializing sample data
    static {
        // Sample data for patients
        List<Patient> allPatients = PatientDAO.getAllPatients();
        
        // Create billing records and add them to the list
        billings.add(new Billing(1, allPatients.get(0), 3300, 3000));
        billings.add(new Billing(2, allPatients.get(1), 1800, 1300));
    
    }
    
    // Method to retrieve all billing records
    public List<Billing> getAllBillings() {
        return billings;
    }
    
    // Method to retrieve a billing record by ID
    public Billing getBilling(int Id) {
        for (Billing billing : billings) {
            if (billing.getInvoiceNo() == Id) {
                return billing;
            }
        }
        return null;
    }

    // Method to add a new billing record
    public void addBilling(Billing billing) {
        int newUserId = getNextBillId();
        billing.setInvoiceNo(newUserId);
        billings.add(billing);
    }
    
    // Method to update an existing billing record
    public void updateBilling(Billing updatedBilling) {
        for (int i = 0; i < billings.size(); i++) {
            Billing billing = billings.get(i);
            if (billing.getInvoiceNo() == updatedBilling.getInvoiceNo()) {
                billings.set(i, updatedBilling);
                System.out.println("Billing updated: " + updatedBilling);
                return;
            }
        }
    }
    
    // Method to delete billing records by invoice number
    public void deleteBills(int No) {
        billings.removeIf(Billing -> Billing.getInvoiceNo() == No);
    }
     
    // Method to get the next available billing ID
    public int getNextBillId() {
        // Initialize maxUserId with a value lower than any possible userId
        int maxUserId = Integer.MIN_VALUE;

        // Iterate through the list to find the maximum userId
        for (Billing Bill : billings) {
            int userId = Bill.getInvoiceNo();
            if (userId > maxUserId) {
                maxUserId = userId;
            }
        }
        // Increment the maximum userId to get the next available userId
        return maxUserId + 1;
    }
}


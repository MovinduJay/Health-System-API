
package com.example.model;


public class Billing {
    
    private int InvoiceNo;
    private Patient patient;
    private double payment;
    private double Total;
    private double Balance;
    
    public Billing(){
    
    }
    
    public Billing(int InvoiceNo, Patient patient,double payment,double Total  ){
        this.InvoiceNo = InvoiceNo;
        this.patient = patient;
        this.Total = Total;
        this.payment = payment;
        this.Balance = payment - Total;
    }
    //getters and setters
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    public int getInvoiceNo() {
        return InvoiceNo;
    }

    public void setInvoiceNo(int InvoiceNo) {
        this.InvoiceNo = InvoiceNo;
    }
    
    public double getPayment() {
        return payment;
    }

    public void setPayment(double Payment) {
        this.payment = Payment;
    }
    
    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        this.Total = total;
    }
    
    public double getBalance() {
        return Balance;
    }
    
    public void setBalance(double balance) {
        this.Balance = balance;
    }
}

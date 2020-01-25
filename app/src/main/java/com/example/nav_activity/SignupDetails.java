package com.example.nav_activity;

import java.io.Serializable;

public class SignupDetails implements Serializable {

    private String customerName;
    private String customerEmail;
    private String customerMobileNo;
    private int customerAge;
    private String customerAddress;
    private String customerImage;
    private String password;

    /*public SignupDetails(String customerName, String customerEmail, String customerMobileNo, int customerAge, String customerAddress, String customerImage, String password) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerMobileNo = customerMobileNo;
        this.customerAge = customerAge;
        this.customerAddress = customerAddress;
        this.customerImage = customerImage;
        this.password = password;
    }*/

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerMobileNo() {
        return customerMobileNo;
    }

    public void setCustomerMobileNo(String customerMobileNo) {
        this.customerMobileNo = customerMobileNo;
    }

    public int getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(int customerAge) {
        this.customerAge = customerAge;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerImage() {
        return customerImage;
    }

    public void setCustomerImage(String customerImage) {
        this.customerImage = customerImage;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

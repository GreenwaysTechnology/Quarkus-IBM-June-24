package com.ibm.rest.response.enity;

public class Customer {
    private Integer customerId;
    private String customerName;
    private String city;

    public Customer() {
    }

    public Customer(Integer customerId, String customerName, String city) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

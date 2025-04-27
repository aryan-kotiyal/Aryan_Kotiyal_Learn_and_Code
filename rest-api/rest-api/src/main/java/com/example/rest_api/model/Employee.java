package com.example.rest_api.model;

public class Employee {
    private String employeeId;
    private String employeeName;
    private String employeePhoneNumber;

    public Employee() {
    }

    public Employee(String employeeId, String employeeName, String employeePhoneNumber) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeePhoneNumber = employeePhoneNumber;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePhoneNumber() {
        return employeePhoneNumber;
    }

    public void setEmployeePhoneNumber(String employeePhoneNumber) {
        this.employeePhoneNumber = employeePhoneNumber;
    }

}

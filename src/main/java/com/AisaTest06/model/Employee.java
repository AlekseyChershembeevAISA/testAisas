package com.AisaTest06.model;

import java.util.Objects;

public class Employee {
    private int employeeId;
    private String fullname;
    private String birthDate;
    private String email;
    private int companyId;

    public Employee(){

    }

    public Employee(int employeeId, String fullname, String birthDate, String email, int companyId) {
        this.employeeId = employeeId;
        this.fullname = fullname;
        this.birthDate = birthDate;
        this.email = email;
        this.companyId = companyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId &&
                companyId == employee.companyId &&
                Objects.equals(fullname, employee.fullname) &&
                Objects.equals(birthDate, employee.birthDate) &&
                Objects.equals(email, employee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, fullname, birthDate, email, companyId);
    }

    public Employee(String fullname, String birthDate, String email, int companyId) {
        this.fullname = fullname;
        this.birthDate = birthDate;
        this.email = email;
        this.companyId = companyId;
    }

    public Employee(int employeeId) {
        this.employeeId = employeeId;
    }

    public Employee(int employeeId, String fullname) {
        this.employeeId = employeeId;
        this.fullname = fullname;
    }

    public Employee(String fullname){
        this.fullname = fullname;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", fullname='" + fullname + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", email='" + email + '\'' +
                ", companyId=" + companyId +
                '}';
    }
}

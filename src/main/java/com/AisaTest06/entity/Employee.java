package com.AisaTest06.entity;

import java.util.Objects;

@SuppressWarnings("ALL")
public class Employee {
    private int employeeId;
    private String fullName;
    private String birthDate;
    private String email;
    private int companyId;
    private String nameCompany;

    public Employee() {

    }


    public Employee(int employeeId, String fullName, String birthDate, String email, int companyId, String nameCompany) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.email = email;
        this.companyId = companyId;
        this.nameCompany = nameCompany;
    }


    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", fullName='" + fullName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", email='" + email + '\'' +
                ", companyId=" + companyId +
                ", nameCompany='" + nameCompany + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId &&
                companyId == employee.companyId &&
                Objects.equals(fullName, employee.fullName) &&
                Objects.equals(birthDate, employee.birthDate) &&
                Objects.equals(email, employee.email) &&
                Objects.equals(nameCompany, employee.nameCompany);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, fullName, birthDate, email, companyId, nameCompany);
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

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public Employee(String fullname, String birthDate, String email, int companyId, String nameCompany) {
        this.companyId = companyId;
        this.fullName = fullname;
        this.birthDate = birthDate;
        this.email = email;
        this.nameCompany = nameCompany;
    }


}

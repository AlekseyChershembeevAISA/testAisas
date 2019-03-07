package com.AisaTest06.model;

public class Company {
    private int companyId;
    private String name;
    private long nip;
    private String address;
    private long phone;

    public Company(){

    }

    public Company(String name){ this.name = name; }

    public Company(int companyId) {
        this.companyId = companyId;
    }

    public Company(int companyId, String name) {
        this.companyId = companyId;
        this.name = name;
    }

    public Company(int companyId, String name, long nip, String address, long phone) {
        this.companyId = companyId;
        this.name = name;
        this.nip = nip;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", name='" + name + '\'' +
                ", nip=" + nip +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                '}';
    }

    public Company(String name, long nip, String address, long phone) {
        this.name = name;
        this.nip = nip;
        this.address = address;
        this.phone = phone;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNip() {
        return nip;
    }

    public void setNip(long nip) {
        this.nip = nip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
}

package com.AisaTest06.view;

import com.AisaTest06.dao.DAO;
import com.AisaTest06.model.Company;
import com.AisaTest06.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class Lists {

    private DAO dao = new DAO();
    public Lists(){
        dao.dataSource();
    }

    public List<Company> fillingListCompany() {
        List<Company> companyIds = new ArrayList<>();

        for (int i = 0; i < dao.selectAllCompanies().size(); i++) {
            companyIds.add(
                    new Company(dao.selectAllCompanies().get(i).getCompanyId(),
                            dao.selectAllCompanies().get(i).getName()));
        }

        return companyIds;
    }


    public List<Employee> fillingListEmployee() {
        List<Employee> employeeIds = new ArrayList<>();

        for (int i = 0; i < dao.selectAllEmployees().size(); i++) {
            employeeIds.add(
                    new Employee(dao.selectAllEmployees().get(i).getEmployeeId(),
                            dao.selectAllEmployees().get(i).getFullname()));
        }

        return employeeIds;
    }

}

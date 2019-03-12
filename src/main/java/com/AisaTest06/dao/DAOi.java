package com.AisaTest06.dao;

import com.AisaTest06.model.Company;
import com.AisaTest06.model.Employee;

import javax.sql.DataSource;
import java.util.List;

public interface DAOi {
     DataSource dataSource();

     Company insertCompany(Company company);
     Employee insertEmployee(Employee employee);

     Employee editEmployee(Employee employee);
     Company editCompany(Company company);

     int deleteCompany(Company company);
     int deleteEmployee(Employee employee);

     List<Company> selectAllCompanies();
     List<Employee> selectAllEmployees();

     List<Company>searchAllCompanies(String search);
     List<Employee>searchAllEmployees(String search);

     boolean checkCompanyByName(String name);
     boolean checkEmployeeByName(String fullName);
}

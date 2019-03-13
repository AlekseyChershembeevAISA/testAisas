package com.AisaTest06.dao.daoInterfaces;


import com.AisaTest06.Entity.Employee;

import java.util.List;

public interface EmployeeDao {

    Employee insertEmployee(Employee employee);

    Employee editEmployee(Employee employee);

    int deleteEmployee(Employee employee);

    List<Employee> selectAllEmployees();

    List<Employee>searchAllEmployees(String search);



}

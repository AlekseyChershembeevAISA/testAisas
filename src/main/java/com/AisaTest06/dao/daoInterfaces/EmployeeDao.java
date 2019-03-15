package com.AisaTest06.dao.daoInterfaces;


import com.AisaTest06.entity.Employee;

import java.util.List;

public interface EmployeeDao {

    Employee insertEmployee(Employee employee);

    Employee editEmployee(Employee employee);

    //будем удалять по id
    int deleteEmployee(int employeeid);

    List<Employee> selectAllEmployees();

    List<Employee>searchAllEmployees(String search);



}

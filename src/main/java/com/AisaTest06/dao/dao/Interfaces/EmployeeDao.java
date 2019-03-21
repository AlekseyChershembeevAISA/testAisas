package com.AisaTest06.dao.dao.Interfaces;


import com.AisaTest06.entity.Employee;

import java.util.List;

@SuppressWarnings("ALL")
public interface EmployeeDao {

    void insertEmployee(Employee employee);

    void editEmployee(Employee employee);

    //будем удалять по id
    void deleteEmployee(int employeeid);

    List<Employee> selectAllEmployees();

    List<Employee> searchAllEmployees(String search);


}

package com.AisaTest06.dao.dao.interfaces;


import com.AisaTest06.entity.Company;
import com.AisaTest06.entity.Employee;

import java.util.List;

@SuppressWarnings("ALL")
public interface EmployeeDao {
    /**
    Интеррфейс для
    добавления сотрудника 
     **/
    void insertEmployee(Employee employee);
    /**
    Интерфейс для
    редактирования сотрудника
     **/
    void editEmployee(Employee employee);
    /**
    Интерфейс для
    удаления сотрудника 
     **/
    void deleteEmployee(int employeeid);
    /**
    Интерфейс для
    выбора всех сотрудников 
     **/
    List<Employee> selectAllEmployees();
    /**
    Интерфейс для
    выбора сотрудника по полю
     **/
    List<Employee> searchAllEmployees(String search);
    /**
    Интерфейс для
    добавления сотрудника
     **/
    void editEmployeeName(Company company);
}

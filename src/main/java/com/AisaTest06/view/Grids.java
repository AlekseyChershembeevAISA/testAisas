package com.AisaTest06.view;

import com.AisaTest06.Entity.Company;
import com.AisaTest06.Entity.Employee;
import com.vaadin.ui.Grid;

public class Grids extends Grid {

    public Grid<Employee> gridEmployees(){
        Grid<Employee> employeeGrid = new Grid<>();
        employeeGrid.addColumn(Employee::getEmployeeId).setCaption("Id Сотрудника");
        employeeGrid.addColumn(Employee::getFullname).setCaption("ФИО");
        employeeGrid.addColumn(Employee::getBirthDate).setCaption("Дата Рождения");
        employeeGrid.addColumn(Employee::getEmail).setCaption("Email");
        employeeGrid.addColumn(Employee::getCompanyId).setCaption("Id Компании");

        return employeeGrid;
    }

    public Grid<Company> gridCompanies(){
        Grid<Company> companyGrid = new Grid<>();
        companyGrid.addColumn(Company::getCompanyId).setCaption("Id Компании");
        companyGrid.addColumn(Company::getName).setCaption("Имя Компании");
        companyGrid.addColumn(Company::getNip).setCaption("ИНН");
        companyGrid.addColumn(Company::getAddress).setCaption("адрес");
        companyGrid.addColumn(Company::getPhone).setCaption("Телефон");

        return companyGrid;
    }


}

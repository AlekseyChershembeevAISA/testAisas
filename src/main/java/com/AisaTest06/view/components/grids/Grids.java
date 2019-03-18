package com.AisaTest06.view.components.grids;

import com.AisaTest06.entity.Company;
import com.AisaTest06.entity.Employee;
import com.vaadin.ui.Grid;

public class Grids extends Grid {

    public Grid<Employee> gridEmployees() {
        Grid<Employee> employeeGrid = new Grid<>();
        employeeGrid.addColumn(Employee::getFullName).setCaption("ФИО");
        employeeGrid.addColumn(Employee::getBirthDate).setCaption("Дата Рождения");
        employeeGrid.addColumn(Employee::getEmail).setCaption("Email");
        employeeGrid.addColumn(Employee::getNameCompany).setCaption("Имя компании");


        return employeeGrid;
    }

    public Grid<Company> gridCompanies() {
        Grid<Company> companyGrid = new Grid<>();
        companyGrid.addColumn(Company::getName).setCaption("Имя Компании");
        companyGrid.addColumn(Company::getNip).setCaption("ИНН");
        companyGrid.addColumn(Company::getAddress).setCaption("Адрес");
        companyGrid.addColumn(Company::getPhone).setCaption("Телефон");

        return companyGrid;
    }




}

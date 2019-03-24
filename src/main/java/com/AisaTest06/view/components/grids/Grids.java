package com.AisaTest06.view.components.grids;

import com.AisaTest06.entity.Company;
import com.AisaTest06.entity.Employee;
import com.AisaTest06.view.windows.EditCompanyWindow;
import com.AisaTest06.view.windows.EditEmployeeWindow;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;

import java.util.logging.Logger;

public class Grids extends Grid {

    private static Logger logger = Logger.getLogger(Grids.class.getName());

    public Grid<Employee> gridEmployees() {
        Grid<Employee> employeeGrid = new Grid<>();
        employeeGrid.addColumn(Employee::getFullName).setCaption("ФИО");
        employeeGrid.addColumn(Employee::getBirthDate).setCaption("Дата Рождения");
        employeeGrid.addColumn(Employee::getEmail).setCaption("Email");
        employeeGrid.addColumn(Employee::getNameCompany).setCaption("Имя компании");

        employeeGrid.addItemClickListener(listen -> {
            if (listen.getMouseEventDetails().isDoubleClick()) {
                UI.getCurrent().addWindow(new EditEmployeeWindow(listen.getItem()));
                logger.info("Выведено окно с редакцией сотрудника из grid "+ listen.getItem());


            }
        });


        return employeeGrid;

    }

    public static Grid<Company> gridCompanies() {
        Grid<Company> companyGrid = new Grid<>();
        companyGrid.addColumn(Company::getName).setCaption("Имя Компании");
        companyGrid.addColumn(Company::getNip).setCaption("ИНН");
        companyGrid.addColumn(Company::getAddress).setCaption("адрес");
        companyGrid.addColumn(Company::getPhone).setCaption("Телефон");

        companyGrid.addItemClickListener(listen -> {
            if (listen.getMouseEventDetails().isDoubleClick()) {
                UI.getCurrent().addWindow(new EditCompanyWindow(listen.getItem()));
                logger.info("Выведено окно с редакцией компании из grid " + listen
                .getItem());
            }
        });
        return companyGrid;


    }
}


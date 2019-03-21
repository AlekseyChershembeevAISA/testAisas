package com.AisaTest06.view.windows;


import com.AisaTest06.dao.CompanyDaoImpl;
import com.AisaTest06.dao.EmployeeDaoImpl;
import com.AisaTest06.dao.dao.interfaces.CompanyDao;
import com.AisaTest06.dao.dao.interfaces.EmployeeDao;
import com.AisaTest06.entity.Company;
import com.AisaTest06.entity.Employee;
import com.AisaTest06.view.components.layouts.MainLayout;
import com.AisaTest06.view.components.textfields.fieldsEmployee;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import java.lang.String;

import java.util.List;
import java.util.logging.Logger;

@SuppressWarnings("ALL")
public class AddEmployeeWindow extends Window {


    private static Logger logger = Logger.getLogger(AddEmployeeWindow.class.getName());

    public AddEmployeeWindow() {

        setWidth(420f, Unit.PIXELS);

        FormLayout content = new FormLayout();

        content.setMargin(true);

        setModal(true);

        EmployeeDao employeeDao = new EmployeeDaoImpl();
        CompanyDao companyDao = new CompanyDaoImpl();
        center();
        setClosable(true);
        setDraggable(false);
        //setResizeLazy(true);

        setStyleName("Добавить нового сотрудника");

        List<Company> companyList = companyDao.selectAllCompanies();

        ComboBox<Company> selectAllCompanies = new ComboBox<>("Выбрать компанию");



        selectAllCompanies.setItems(companyList);
        selectAllCompanies.setItemCaptionGenerator(Company::getName);
        selectAllCompanies.setEmptySelectionAllowed(false);

        Button addEmployee = new com.vaadin.ui.Button("Добавить");
        addEmployee.setSizeFull();
        addEmployee.setStyleName(ValoTheme.BUTTON_PRIMARY);
        addEmployee.setIcon(VaadinIcons.INSERT);


        Button cancel = new Button("Отменить", clickEvent -> close());

        cancel.setSizeFull();

        fieldsEmployee fieldsEmployee = new fieldsEmployee();

        TextField fullNameTextField = fieldsEmployee.getFullName();
        DateField dateField = fieldsEmployee.getDateField();
        TextField emailField = fieldsEmployee.getEmailTextField();

        final String[] fullNameArr = {""};
        final String[] dateFieldArr = {""};
        final String[] emailArr = {""};
        final String[] companyNameArr = {""};
        final int[] companyId = {0};


        selectAllCompanies.addValueChangeListener(valueChangeEvent -> {
            companyId[0] = valueChangeEvent.getValue().getCompanyId();
            companyNameArr[0] = valueChangeEvent.getValue().getName();
            fieldsEmployee.check(selectAllCompanies);
        });

        content.addComponents(selectAllCompanies, fullNameTextField, dateField, emailField, addEmployee, cancel);

        setContent(content);

        fullNameTextField.setRequiredIndicatorVisible(true);
        dateField.setRequiredIndicatorVisible(true);
        emailField.setRequiredIndicatorVisible(true);


        fullNameTextField.addValueChangeListener(valueChangeEvent ->
                fullNameArr[0] = valueChangeEvent.getValue()
        );

        dateField.addValueChangeListener(valueChangeEvent ->
                dateFieldArr[0] = String.valueOf(valueChangeEvent.getValue()));

        emailField.addValueChangeListener(valueChangeEvent ->
                emailArr[0] = valueChangeEvent.getValue());


        addEmployee.addClickListener((Button.ClickListener) clickEvent6 -> {

            if (!((fullNameArr[0].isEmpty() || dateFieldArr[0].isEmpty()
                    || emailArr[0].isEmpty()) || companyNameArr[0].isEmpty())) {


                Employee employee;
                try {

                    employee = new Employee(fullNameArr[0], dateFieldArr[0], emailArr[0], companyId[0], companyNameArr[0]);


                    if (!(fullNameArr[0].isEmpty() || dateFieldArr[0].isEmpty() ||
                            emailArr[0].isEmpty())) {
                        employeeDao.insertEmployee(employee);
                        MainLayout.tabSheet.setSelectedTab(MainLayout.tabCompany);
                        MainLayout.tabSheet.setSelectedTab(MainLayout.tabEmployee);

                        close();
                    }
                } catch (NumberFormatException ex) {
                    logger.warning("Неверные данные компании " + ex);
                } catch (NullPointerException ex) {
                    logger.warning("NPE " + ex);
                }
            } else {
                fieldsEmployee.check(fullNameTextField);
                fieldsEmployee.check(dateField);
                fieldsEmployee.check(emailField);
                fieldsEmployee.check(selectAllCompanies);

                logger.warning("Необходимо заполнить все данные сотрудника "
                        + fullNameArr[0] + " " + dateFieldArr[0] + " " + emailArr[0] + " " + companyId[0]);
            }
        });

    }
}




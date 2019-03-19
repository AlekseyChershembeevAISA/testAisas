package com.AisaTest06.view.windows;


import com.AisaTest06.dao.CompanyDaoImpl;
import com.AisaTest06.dao.EmployeeDaoImpl;
import com.AisaTest06.dao.daoInterfaces.CompanyDao;
import com.AisaTest06.dao.daoInterfaces.EmployeeDao;
import com.AisaTest06.entity.Company;
import com.AisaTest06.entity.Employee;
import com.AisaTest06.view.components.layouts.MainLayout;
import com.AisaTest06.view.components.textFields.TextFieldsEmployee;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;
import java.util.logging.Logger;

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


        Button cancel = new Button("Отменить",clickEvent -> close());

        cancel.setSizeFull();

        TextFieldsEmployee textFieldsEmployee = new TextFieldsEmployee();

        TextField fullName = textFieldsEmployee.getFullName();
        DateField dateField = textFieldsEmployee.getDateField();
        TextField email = textFieldsEmployee.getEmail();

        final String[] fullNameArr = {""};
        final String[] dateFieldArr = {""};
        final String[] emailArr = {""};
        final String[] companyNameArr = {""};
        final int[] companyId = {0};


        selectAllCompanies.addValueChangeListener(valueChangeEvent -> {
            companyId[0] = valueChangeEvent.getValue().getCompanyId();
            companyNameArr[0] = valueChangeEvent.getValue().getName();
        });

        content.addComponents(selectAllCompanies,fullName,dateField,email,addEmployee,cancel);

        setContent(content);

        fullName.setRequiredIndicatorVisible(true);
        dateField.setRequiredIndicatorVisible(true);
        email.setRequiredIndicatorVisible(true);


        fullName.addValueChangeListener(valueChangeEvent ->
                fullNameArr[0] = valueChangeEvent.getValue()
        );

        dateField.addValueChangeListener(valueChangeEvent ->
                dateFieldArr[0] = String.valueOf(valueChangeEvent.getValue()));

        email.addValueChangeListener(valueChangeEvent ->
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
                        MainLayout.tabSheet.setSelectedTab(MainLayout.tab1);
                        MainLayout.tabSheet.setSelectedTab(MainLayout.tab2);
                    }
                } catch (NumberFormatException ex) {
                    logger.warning("Неверные данные компании " + ex);
                } catch (NullPointerException ex) {
                    logger.warning("NPE " + ex);
                }
            } else {
                logger.warning("Необходимо заполнить все данные сотрудника "
                        + fullNameArr[0] + " " + dateFieldArr[0] + " " + emailArr[0] + " " + companyId[0]);
            }
        });

    }
}




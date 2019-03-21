package com.AisaTest06.view.windows;

import com.AisaTest06.dao.EmployeeDaoImpl;
import com.AisaTest06.dao.dao.interfaces.EmployeeDao;
import com.AisaTest06.entity.Employee;
import com.AisaTest06.view.components.layouts.MainLayout;
import com.AisaTest06.view.components.textfields.fieldsEmployee;
import com.vaadin.data.HasValue;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.time.LocalDate;
import java.util.logging.Logger;

@SuppressWarnings("ALL")
public class EditEmployeeWindow extends Window {

    private static Logger logger = Logger.getLogger(EditEmployeeWindow.class.getName());

    public EditEmployeeWindow(Employee employee) {
        setStyleName("Редактировать сотрудника");
        VerticalLayout editVerticalLayout = new VerticalLayout();

        center();
        setClosable(true);
        setDraggable(false);
        setModal(true);
        setContent(editVerticalLayout);
        setWidth(400f, Unit.PIXELS);
        setHeight(350f, Unit.PIXELS);

        fieldsEmployee fieldsEmployee = new fieldsEmployee();
        TextField fullName = fieldsEmployee.getFullName();
        DateField dateField = fieldsEmployee.getDateField();
        TextField email = fieldsEmployee.getEmailTextField();

        EmployeeDao employeeDao = new EmployeeDaoImpl();



        final String[] fullNameArr = {employee.getFullName()};
        final String[] dateArr = {employee.getBirthDate()};
        final String[] emailArr = {employee.getEmail()};

        final String[] companyNameArr = {employee.getNameCompany()};

        Button editEmployee = new Button("Редактировать");
        editEmployee.setSizeFull();
        editEmployee.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        editEmployee.setIcon(VaadinIcons.EDIT);

        Button cancel = new Button("Отменить", clickEvent -> close());

        cancel.setSizeFull();

        fullName.setRequiredIndicatorVisible(true);
        dateField.setRequiredIndicatorVisible(true);
        email.setRequiredIndicatorVisible(true);

        fullName.setValue(fullNameArr[0]);
        dateField.setValue(LocalDate.parse(dateArr[0]));
        email.setValue(emailArr[0]);


        editVerticalLayout.addComponents(fullName, dateField,
                email, editEmployee, cancel);

        dateField.addValueChangeListener(
                (HasValue.ValueChangeListener<LocalDate>) valueChangeEvent -> {
                    LocalDate date = valueChangeEvent.getValue();
                    try {
                        dateArr[0] = date.toString();
                    }catch (NullPointerException ex){
                        logger.warning("NPE не заполнена дата рождения " + ex);
                    }

                });

        email.addValueChangeListener(
                (HasValue.ValueChangeListener<String>) valueChangeEvent ->
                        emailArr[0] = valueChangeEvent.getValue());

        fullName.addValueChangeListener(
                (HasValue.ValueChangeListener<String>) valueChangeEvent ->
                        fullNameArr[0] = valueChangeEvent.getValue());

        editEmployee.addClickListener((Button.ClickListener) clickEvent13 -> {

            try {

                employee.setNameCompany(fullNameArr[0]);
                employee.setBirthDate(dateArr[0]);
                employee.setEmail(emailArr[0]);
                employee.setFullName(fullNameArr[0]);


                if (!(fullNameArr[0].isEmpty() || dateArr[0].isEmpty() ||
                        emailArr[0].isEmpty() || companyNameArr[0].isEmpty())) {

                    employeeDao.editEmployee(employee);
                    MainLayout.tabSheet.setSelectedTab(MainLayout.tabCompany);
                    MainLayout.tabSheet.setSelectedTab(MainLayout.tabEmployee);
                    close();

                } else {
                    fieldsEmployee.check(fullName);
                    fieldsEmployee.check(dateField);
                    fieldsEmployee.check(email);
                    logger.warning("Неверная редакция сотрудника " + employee);
                }

            } catch (NullPointerException ex) {
                logger.warning("NPE сотрудника " + employee + " " + ex)
                ;
            }
        });

    }


}

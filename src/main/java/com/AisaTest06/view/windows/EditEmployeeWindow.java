package com.AisaTest06.view.windows;

import com.AisaTest06.dao.EmployeeDaoImpl;
import com.AisaTest06.dao.daoInterfaces.EmployeeDao;
import com.AisaTest06.entity.Employee;
import com.AisaTest06.view.components.textFields.TextFieldsEmployee;
import com.vaadin.data.HasValue;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

public class EditEmployeeWindow extends Window {

    private static Logger logger = Logger.getLogger(EditEmployeeWindow.class.getName());

    public EditEmployeeWindow() {
        setStyleName("Редактировать сотрудника");
        VerticalLayout editVerticalLayout = new VerticalLayout();

        center();
        setClosable(true);
        setDraggable(false);
        setModal(true);
        setContent(editVerticalLayout);
        setWidth(400f,Unit.PIXELS);
        setHeight(450f,Unit.PIXELS);
        TextFieldsEmployee textFieldsEmployee = new TextFieldsEmployee();
        TextField fullName = textFieldsEmployee.getFullName();
        DateField dateField = textFieldsEmployee.getDateField();
        TextField email = textFieldsEmployee.getEmail();

        EmployeeDao employeeDao = new EmployeeDaoImpl();

        List<Employee> employeeList = employeeDao.selectAllEmployees();


        ComboBox<Employee> selectAllEmployees = new ComboBox<>("Выбрать сотрудника");
        selectAllEmployees.setSizeFull();

        selectAllEmployees.setItems(employeeList);
        selectAllEmployees.setItemCaptionGenerator(Employee::getFullName);
        selectAllEmployees.setEmptySelectionAllowed(false);


        final int[] employeeIdArr = {0};
        final String[] fullNameArr = {""};
        final String[] dateArr = {""};
        final String[] emailArr = {""};
        final int[] companyIdArr = {0};
        final String[] companyNameArr = {""};

        Button editEmployee = new Button("Редактировать");
        editEmployee.setSizeFull();
        editEmployee.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        editEmployee.setIcon(VaadinIcons.EDIT);

        Button cancel = new Button("Отменить",clickEvent -> close());

        cancel.setSizeFull();

        fullName.setRequiredIndicatorVisible(true);
        dateField.setRequiredIndicatorVisible(true);
        email.setRequiredIndicatorVisible(true);


        editVerticalLayout.addComponents(selectAllEmployees, fullName, dateField,
                email, editEmployee,cancel);

        dateField.addValueChangeListener(
                (HasValue.ValueChangeListener<LocalDate>) valueChangeEvent -> {
                    LocalDate date = valueChangeEvent.getValue();
                    dateArr[0] = date.toString();
                });

        email.addValueChangeListener(
                (HasValue.ValueChangeListener<String>) valueChangeEvent ->
                        emailArr[0] = valueChangeEvent.getValue());

        fullName.addValueChangeListener(
                (HasValue.ValueChangeListener<String>) valueChangeEvent ->
                        fullNameArr[0] = valueChangeEvent.getValue());


        selectAllEmployees.setItems(employeeList);
        selectAllEmployees.setItemCaptionGenerator(Employee::getFullName);


        selectAllEmployees.addValueChangeListener(valueChangeEvent -> {
            employeeIdArr[0] = valueChangeEvent.getValue().getEmployeeId();
            companyIdArr[0] = valueChangeEvent.getValue().getEmployeeId();
            companyNameArr[0] = valueChangeEvent.getValue().getNameCompany();
            logger.info("Выбран сотрудник " + valueChangeEvent.getValue());
        });


        editEmployee.addClickListener((Button.ClickListener) clickEvent13 -> {


            Employee employee = null;
            try {
                employee = new Employee(employeeIdArr[0],
                        fullNameArr[0], dateArr[0], emailArr[0], companyIdArr[0], companyNameArr[0]);


                if (!(fullNameArr[0].isEmpty() || dateArr[0].isEmpty() ||
                        emailArr[0].isEmpty() || companyNameArr[0].isEmpty())) {

                    employeeDao.editEmployee(employee);



                } else {
                    logger.warning("Неверная редакция сотрудника " + employee);
                }

            } catch (NullPointerException ex) {
                logger.warning("NPE сотрудника " + employee + " " + ex)
                ;
            }
        });

    }


}

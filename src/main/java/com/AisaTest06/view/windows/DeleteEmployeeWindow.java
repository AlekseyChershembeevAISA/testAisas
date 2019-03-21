package com.AisaTest06.view.windows;

import com.AisaTest06.dao.EmployeeDaoImpl;
import com.AisaTest06.dao.dao.interfaces.EmployeeDao;
import com.AisaTest06.entity.Employee;
import com.AisaTest06.view.components.layouts.MainLayout;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class DeleteEmployeeWindow extends Window {

    private static Logger logger = Logger.getLogger(DeleteEmployeeWindow.class.getName());

    public DeleteEmployeeWindow() {
        setStyleName("Удалить сотрудника");

        center();
        setClosable(true);
        setDraggable(false);
        setModal(true);
        setWidth(270f, Unit.PIXELS);
        //setResizeLazy(true);

        EmployeeDao employeeDao = new EmployeeDaoImpl();

        Button deleteEmployee = new Button("Удалить");
        deleteEmployee.setSizeFull();
        deleteEmployee.setStyleName(ValoTheme.BUTTON_DANGER);
        deleteEmployee.setIcon(VaadinIcons.MINUS);

        Button cancelButton = new Button("Отменить", clickEvent -> close());

        cancelButton.setSizeFull();

        CheckBoxGroup<Employee> selectAllEmployees = new CheckBoxGroup<>("Выбрать сотрудника");

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponents(selectAllEmployees, deleteEmployee, cancelButton);
        setContent(verticalLayout);

        List<Employee> employeeList = employeeDao.selectAllEmployees();

        selectAllEmployees.setItems(employeeList);
        selectAllEmployees.setItemCaptionGenerator(Employee::getFullName);

        selectAllEmployees.addValueChangeListener(valueChangeEvent -> {

            Set<Employee> employeeSet = valueChangeEvent.getValue();


            logger.info("Выраны сотрудники " + employeeSet);

            ArrayList<Employee> employeeArrayList = new ArrayList<>(employeeSet);

            deleteEmployee.addClickListener(clickEvent -> {

                for (int i = 0; i < employeeSet.size(); i++) {
                    if (!employeeArrayList.isEmpty()) {
                        employeeDao.deleteEmployee(employeeArrayList.get(i).getEmployeeId());
                        MainLayout.tabSheet.setSelectedTab(MainLayout.tabCompany);
                        MainLayout.tabSheet.setSelectedTab(MainLayout.tabEmployee);
                        close();

                        logger.info("сотрудник  успешно удален " + employeeArrayList.get(i));
                    } else {
                        logger.warning("Невозможно удалить сотрудника");
                    }
                }
                    close();
            });

        });

    }
}

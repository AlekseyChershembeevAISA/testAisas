package com.AisaTest06.view.windows;

import com.AisaTest06.dao.EmployeeDaoImpl;
import com.AisaTest06.dao.daoInterfaces.EmployeeDao;
import com.AisaTest06.entity.Employee;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import java.util.List;
import java.util.logging.Logger;

public class DeleteEmployeeWindow extends Window {

    private static Logger logger = Logger.getLogger(DeleteEmployeeWindow.class.getName());

    public DeleteEmployeeWindow() {
        setStyleName("Удалить сотрудника");

        center();
        setClosable(true);
        setDraggable(false);

        EmployeeDao employeeDao = new EmployeeDaoImpl();
        List<Employee> employeeList = employeeDao.selectAllEmployees();

        ComboBox<Employee> selectAllEmployees = new ComboBox<>("Выбрать сотрудника");

        selectAllEmployees.setItems(employeeList);
        selectAllEmployees.setItemCaptionGenerator(Employee::getFullName);
        selectAllEmployees.setEmptySelectionAllowed(false);

        selectAllEmployees.addValueChangeListener(event -> {
            Employee employee = event.getValue();

            logger.info("Выбрана компания в combobox " + employee.getFullName());
        });

        Button deleteCompany = new Button("Удалить сотрудника");


        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponents(selectAllEmployees, deleteCompany);

        setContent(verticalLayout);

        int[] employeeId = {0};
        selectAllEmployees.addValueChangeListener(valueChangeEvent -> {
            employeeId[0] = valueChangeEvent.getValue().getEmployeeId();
        });

        deleteCompany.addClickListener(clickEvent -> {
            if (employeeId[0] != 0) {
                employeeDao.deleteEmployee(employeeId[0]);
                Page.getCurrent().reload();
                logger.info("Сотрудник успешно удален " + employeeId[0]);
            } else {
                logger.warning("Невозможно удалить сотрудника");
            }

        });
    }


}

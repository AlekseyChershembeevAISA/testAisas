package com.AisaTest06.view.components.layouts;

import com.AisaTest06.dao.CompanyDaoImpl;
import com.AisaTest06.dao.EmployeeDaoImpl;
import com.AisaTest06.dao.dao.interfaces.CompanyDao;
import com.AisaTest06.dao.dao.interfaces.EmployeeDao;
import com.AisaTest06.entity.Company;
import com.AisaTest06.entity.Employee;
import com.AisaTest06.view.components.grids.Grids;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

import java.util.logging.Logger;

import static com.AisaTest06.view.components.layouts.HeadLayout.searchField;


@SuppressWarnings("ALL")
public class MainLayout extends VerticalLayout {

    private static Logger logger = Logger.getLogger(MainLayout.class.getName());

    public static final TabSheet tabSheet = new TabSheet();

    public static final HorizontalLayout tabCompany = new HorizontalLayout();
    public static final HorizontalLayout tabEmployee = new HorizontalLayout();

    public MainLayout() {

        CompanyDao companyDao = new CompanyDaoImpl();
        EmployeeDao employeeDao = new EmployeeDaoImpl();

        tabSheet.addTab(tabCompany, "Компании");
        tabSheet.addTab(tabEmployee, "Сотрудники");

        HeadLayout headLayout = new HeadLayout();

        Grids grids = new Grids();


        Grid<Company> companyGrid = Grids.gridCompanies();
        Grid<Employee> employeeGrid = grids.gridEmployees();

        companyGrid.setSizeFull();
        employeeGrid.setSizeFull();


        addComponent(headLayout);
        addComponent(tabSheet);
        addComponent(companyGrid);


        tabSheet.addSelectedTabChangeListener(
                (TabSheet.SelectedTabChangeListener) e -> {
                    if (tabSheet.getSelectedTab().equals(tabCompany)) {
                        companyGrid.setItems(companyDao.selectAllCompanies());
                        addComponent(companyGrid);
                        removeComponent(employeeGrid);


                    } else if (tabSheet.getSelectedTab().equals(tabEmployee)) {
                        employeeGrid.setItems(employeeDao.selectAllEmployees());
                        addComponent(employeeGrid);
                        removeComponent(companyGrid);

                    }
                });

        companyGrid.setItems(companyDao.selectAllCompanies());

        searchField.addValueChangeListener(e -> {
            if (tabSheet.getSelectedTab().equals(tabCompany)) {
                companyGrid.setItems(companyDao.searchAllCompanies(searchField.getValue()));
                addComponent(companyGrid);
                removeComponent(employeeGrid);

                logger.info("Выбран tabCompany с search " + searchField.getValue());
            } else if (tabSheet.getSelectedTab().equals(tabEmployee)) {
                employeeGrid.setItems(employeeDao.searchAllEmployees(searchField.getValue()));
                addComponent(employeeGrid);
                removeComponent(companyGrid);

                logger.info("Выбран tabEmployee с search " + searchField.getValue());
            }
        });

    }
}

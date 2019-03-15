package com.AisaTest06.view.components.layouts;

import com.AisaTest06.dao.CompanyDaoImpl;
import com.AisaTest06.dao.EmployeeDaoImpl;
import com.AisaTest06.dao.daoInterfaces.CompanyDao;
import com.AisaTest06.dao.daoInterfaces.EmployeeDao;
import com.AisaTest06.entity.Company;
import com.AisaTest06.entity.Employee;
import com.AisaTest06.view.components.grids.Grids;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

import static com.AisaTest06.view.components.layouts.HeadLayout.search;

import java.util.logging.Logger;

public class MainLayout extends VerticalLayout {

    private static Logger logger = Logger.getLogger(MainLayout.class.getName());

    public static final TabSheet tabSheet = new TabSheet();

    //табы
    static HorizontalLayout tab1 = new HorizontalLayout();
    static HorizontalLayout tab2 = new HorizontalLayout();


    public MainLayout() {

        CompanyDao companyDao = new CompanyDaoImpl();
        EmployeeDao employeeDao = new EmployeeDaoImpl();

        tabSheet.addTab(tab1, "Компании");
        tabSheet.addTab(tab2, "Сотрудники");
        tabSheet.setSelectedTab(tab1);

        HeadLayout headLayout = new HeadLayout();
        //таблицы
        Grids grids = new Grids();

        //таблицы с компаниями и сотрудниками
        Grid<Company> companyGrid = grids.gridCompanies();
        Grid<Employee> employeeGrid = grids.gridEmployees();
        companyGrid.setSizeFull();
        employeeGrid.setSizeFull();


        addComponent(headLayout);
        addComponent(tabSheet);
        addComponent(companyGrid);

        tabSheet.addSelectedTabChangeListener(
                (TabSheet.SelectedTabChangeListener) e -> {
                    if (tabSheet.getSelectedTab().equals(tab1)) {
                        companyGrid.setItems(companyDao.selectAllCompanies());
                        addComponent(companyGrid);
                        removeComponent(employeeGrid);

                        logger.info("Выбран tab1");

                    } else if (tabSheet.getSelectedTab().equals(tab2)) {
                        employeeGrid.setItems(employeeDao.selectAllEmployees());
                        addComponent(employeeGrid);
                        removeComponent(companyGrid);

                        logger.info("Выбран tab2");

                    }
                });

        companyGrid.setItems(companyDao.selectAllCompanies());

        search.addValueChangeListener(e -> {
            if (tabSheet.getSelectedTab().equals(tab1)) {
                companyGrid.setItems(companyDao.searchAllCompanies(search.getValue()));
                addComponent(companyGrid);
                removeComponent(employeeGrid);
                logger.info("Выбран tab1 с search " + search.getValue());
            } else if (tabSheet.getSelectedTab().equals(tab2)) {
                employeeGrid.setItems(employeeDao.searchAllEmployees(search.getValue()));
                addComponent(employeeGrid);
                removeComponent(companyGrid);
                logger.info("Выбран tab2 с search " + search.getValue());
            }
        });

    }
}

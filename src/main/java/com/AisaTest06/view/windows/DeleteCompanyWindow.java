package com.AisaTest06.view.windows;

import com.AisaTest06.dao.CompanyDaoImpl;
import com.AisaTest06.dao.daoInterfaces.CompanyDao;
import com.AisaTest06.entity.Company;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import java.util.List;
import java.util.logging.Logger;


public class DeleteCompanyWindow extends Window {

    private static Logger logger = Logger.getLogger(DeleteCompanyWindow.class.getName());

    public DeleteCompanyWindow() {
        setStyleName("Удалить компанию");
        center();
        setClosable(true);
        setDraggable(false);

        CompanyDao companyDao = new CompanyDaoImpl();
        List<Company>companyList = companyDao.selectAllCompanies();

        ComboBox<Company> selectAllCompanies = new ComboBox<>("Выбрать компанию");

        selectAllCompanies.setItems(companyList);
        selectAllCompanies.setItemCaptionGenerator(Company::getName);
        selectAllCompanies.setEmptySelectionAllowed(false);

        selectAllCompanies.addValueChangeListener(event -> {
            Company company = event.getValue();
            //CompanyIdArr[0] = company.getCompanyId();
            logger.info("Выбрана компания в combobox " + company.getName());
        });

        Button deleteCompany = new Button("Удалить компанию");


        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponents(selectAllCompanies,deleteCompany);

        setContent(verticalLayout);

        int [] companyId ={0} ;
        selectAllCompanies.addValueChangeListener(valueChangeEvent -> {
            companyId[0] = valueChangeEvent.getValue().getCompanyId();
        }) ;

        deleteCompany.addClickListener(clickEvent -> {
            if (companyId[0]!=0) {
                companyDao.deleteCompany(companyId[0]);
                Page.getCurrent().reload();
                logger.info("компания успешно удалена "+ companyId[0]);
            }
            else {
                logger.warning("Невозможно удалить компанию");
            }

        });
    }

    }






package com.AisaTest06.view.windows;

import com.AisaTest06.dao.CompanyDaoImpl;
import com.AisaTest06.dao.daoInterfaces.CompanyDao;
import com.AisaTest06.entity.Company;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;


public class DeleteCompanyWindow extends Window {

    private static Logger logger = Logger.getLogger(DeleteCompanyWindow.class.getName());

    public DeleteCompanyWindow() {
        setStyleName("Удалить компанию");
        setWidth(270f,Unit.PIXELS);
        center();
        setClosable(true);
        setDraggable(false);
        setModal(true);

        CompanyDao companyDao = new CompanyDaoImpl();


        Button deleteCompany = new Button("Удалить");
        deleteCompany.setSizeFull();
        deleteCompany.setStyleName(ValoTheme.BUTTON_DANGER);
        deleteCompany.setIcon(VaadinIcons.MINUS);

        Button cancel = new Button("Отменить", clickEvent -> close());

        cancel.setSizeFull();

        List<Company> companyList = companyDao.selectAllCompanies();

        CheckBoxGroup<Company> selectAllCompanies = new CheckBoxGroup<>("Выбрать компании");
        selectAllCompanies.setSizeFull();

        selectAllCompanies.setItems(companyList);
        selectAllCompanies.setItemCaptionGenerator(Company::getName);

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponents(selectAllCompanies, deleteCompany, cancel);

        setContent(verticalLayout);

        selectAllCompanies.addValueChangeListener(valueChangeEvent -> {

            Set<Company> companySet = valueChangeEvent.getValue();

            logger.info("Выбраны компании " + companySet);

            ArrayList<Company> listCompany = new ArrayList<>(companySet);

            deleteCompany.addClickListener(clickEvent -> {

                for (int i = 0; i < companySet.size(); i++) {
                    if (!listCompany.isEmpty()) {
                        companyDao.deleteCompany(listCompany.get(i).getCompanyId());
                        //  Page.getCurrent().reload();
                        logger.info("компания успешно удалена " + listCompany.get(i));
                    } else {
                        logger.warning("Невозможно удалить компанию");
                    }
                }

            });


        });
    }

}






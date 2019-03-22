package com.AisaTest06.view.windows;

import com.AisaTest06.dao.CompanyDaoImpl;
import com.AisaTest06.dao.dao.interfaces.CompanyDao;
import com.AisaTest06.entity.Company;
import com.AisaTest06.view.components.layouts.MainLayout;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;


@SuppressWarnings("ALL")
public class DeleteCompanyWindow extends Window {

    private static Logger logger = Logger.getLogger(DeleteCompanyWindow.class.getName());

    public DeleteCompanyWindow() {

        setStyleName("Удалить компанию");
        setWidth(270f, Unit.PIXELS);
        center();
        setClosable(true);
        setDraggable(false);
        setModal(true);

        setResizeLazy(false);

        CompanyDao companyDao = new CompanyDaoImpl();


        Button deleteCompanyButton = new Button("Удалить");
        deleteCompanyButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        deleteCompanyButton.setSizeFull();
        deleteCompanyButton.setStyleName(ValoTheme.BUTTON_DANGER);
        deleteCompanyButton.setIcon(VaadinIcons.MINUS);

        Button cancelButton = new Button("Отменить", clickEvent -> close());

        cancelButton.setSizeFull();

        List<Company> companyList = companyDao.selectAllCompanies();

        CheckBoxGroup<Company> selectAllCompanies = new CheckBoxGroup<>("Выбрать компании");
        selectAllCompanies.setSizeFull();

        selectAllCompanies.setItems(companyList);
        selectAllCompanies.setItemCaptionGenerator(Company::getName);

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponents(selectAllCompanies, deleteCompanyButton, cancelButton);

        setContent(verticalLayout);
        /*
         Удаляем выбранные компании из комбобокса по ID компании
        */

        selectAllCompanies.addValueChangeListener(valueChangeEvent -> {

            Set<Company> companySet = valueChangeEvent.getValue();

            logger.info("Выбраны компании " + companySet);

            ArrayList<Company> listCompany = new ArrayList<>(companySet);

            deleteCompanyButton.addClickListener(clickEvent -> {

                for (int i = 0; i < companySet.size(); i++) {
                    if (!listCompany.isEmpty()) {
                        companyDao.deleteCompany(listCompany.get(i).getCompanyId());
                        MainLayout.tabSheet.setSelectedTab(MainLayout.tabEmployee);
                        MainLayout.tabSheet.setSelectedTab(MainLayout.tabCompany);
                        close();

                        logger.info("компания успешно удалена " + listCompany.get(i));
                    } else {
                        logger.warning("Невозможно удалить компанию");
                    }
                }



            });


        });
    }

}






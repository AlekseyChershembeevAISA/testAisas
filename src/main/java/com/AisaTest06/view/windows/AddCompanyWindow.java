package com.AisaTest06.view.windows;


import com.AisaTest06.dao.CompanyDaoImpl;
import com.AisaTest06.dao.daoInterfaces.CompanyDao;
import com.AisaTest06.entity.Company;
import com.AisaTest06.view.components.layouts.MainLayout;
import com.AisaTest06.view.components.textFields.TextFieldsCompany;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import java.util.logging.Logger;

import static com.AisaTest06.view.components.grids.Grids.employeeGrid;

public class AddCompanyWindow extends Window {

    private static Logger logger = Logger.getLogger(AddCompanyWindow.class.getName());


    public AddCompanyWindow() {

        Button addCompany = new Button("Добавить");


        setWidth(420f, Unit.PIXELS);
        setHeight(300f, Unit.PIXELS);
        FormLayout content = new FormLayout();

        content.setMargin(true);

        center();
        setClosable(true);
        setDraggable(false);
        setModal(true);

        CompanyDao companyDao = new CompanyDaoImpl();

        setStyleName("Добавить новую компанию");

        addCompany.setStyleName(ValoTheme.BUTTON_PRIMARY);
        addCompany.setIcon(VaadinIcons.INSERT);
        addCompany.setSizeFull();

        Button cancel = new Button("Отменить");
        cancel.addClickListener(clickEvent -> close());

        cancel.setSizeFull();

        final int[] companyid = {0};
        final String[] companyNameArr = {""};
        final String[] NIPArr = {""};
        final String[] AddressArr = {""};
        final String[] PhoneArr = {""};

        TextFieldsCompany textFieldsCompany = new TextFieldsCompany();
        TextField name = textFieldsCompany.getFullName();
        TextField nip = textFieldsCompany.getNip();
        TextField address = textFieldsCompany.getAddress();
        TextField phone = textFieldsCompany.getPhone();


        content.addComponents(name, nip, address, phone, addCompany, cancel);

        setContent(content);

        name.setRequiredIndicatorVisible(true);
        nip.setRequiredIndicatorVisible(true);
        address.setRequiredIndicatorVisible(true);
        phone.setRequiredIndicatorVisible(true);


        name.addValueChangeListener(valueChangeEvent ->
                companyNameArr[0] = valueChangeEvent.getValue()
        );

        nip.addValueChangeListener(valueChangeEvent ->
                NIPArr[0] = valueChangeEvent.getValue());

        address.addValueChangeListener(valueChangeEvent ->
                AddressArr[0] = valueChangeEvent.getValue());

        phone.addValueChangeListener(valueChangeEvent ->
                PhoneArr[0] = valueChangeEvent.getValue());

        addCompany.addClickListener((Button.ClickListener) clickEvent6 -> {
            Company company;

            if (!(companyNameArr[0].isEmpty() || NIPArr[0].isEmpty()
                    || AddressArr[0].isEmpty() || PhoneArr[0].isEmpty())) {

                try {

                    company = new Company(companyid[0], companyNameArr[0], Long.parseLong(NIPArr[0])
                            , AddressArr[0], Long.parseLong(PhoneArr[0]));

                    if
                    (companyDao.checkCompanyByName(company.getName())) {
                        logger.warning("Такая компания уже существет " + company.getName());
                        company.setName("");

                    } else {

                        companyDao.insertCompany(company);
                       MainLayout.tabSheet.setSelectedTab(MainLayout.tab2);
                       MainLayout.tabSheet.setSelectedTab(MainLayout.tab1);

                    }

                } catch (NumberFormatException ex) {
                    logger.warning("Неверные данные компании " + ex);
                } catch (NullPointerException ex) {
                    logger.warning("NPE " + ex);
                }

            } else {
                logger.warning("Необходимо заполнить все данные компании " +
                        companyNameArr[0] + " " + NIPArr[0] + " " + AddressArr[0] + " " + PhoneArr[0]);
            }

        });

    }
}

















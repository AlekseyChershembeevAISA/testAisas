package com.AisaTest06.view.windows;


import com.AisaTest06.dao.CompanyDaoImpl;
import com.AisaTest06.dao.dao.interfaces.CompanyDao;
import com.AisaTest06.entity.Company;
import com.AisaTest06.view.components.layouts.MainLayout;
import com.AisaTest06.view.components.textfields.fieldsCompany;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import java.util.logging.Logger;

@SuppressWarnings("ALL")
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
        //setResizeLazy(true);

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

        fieldsCompany fieldsCompany = new fieldsCompany();
        TextField nameTextField = fieldsCompany.getFullNameTextField();
        TextField nipTextField = fieldsCompany.getNipTextField();
        TextField address = fieldsCompany.getAddressTextField();
        TextField phone = fieldsCompany.getPhoneTextField();


        content.addComponents(nameTextField, nipTextField, address, phone, addCompany, cancel);

        setContent(content);

        nameTextField.setRequiredIndicatorVisible(true);
        nipTextField.setRequiredIndicatorVisible(true);
        address.setRequiredIndicatorVisible(true);
        phone.setRequiredIndicatorVisible(true);


        nameTextField.addValueChangeListener(valueChangeEvent ->
                companyNameArr[0] = valueChangeEvent.getValue()
        );

        nipTextField.addValueChangeListener(valueChangeEvent ->
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
                        MainLayout.tabSheet.setSelectedTab(MainLayout.tabEmployee);
                        MainLayout.tabSheet.setSelectedTab(MainLayout.tabCompany);
                        close();

                    }

                } catch (NumberFormatException ex) {
                    logger.warning("Неверные данные компании " + ex);
                } catch (NullPointerException ex) {
                    logger.warning("NPE " + ex);
                }

            } else {
                fieldsCompany.check(nameTextField);
                fieldsCompany.check(nipTextField);
                fieldsCompany.check(address);
                fieldsCompany.check(phone);
                logger.warning("Необходимо заполнить все данные компании " +
                        companyNameArr[0] + " " + NIPArr[0] + " " + AddressArr[0] + " " + PhoneArr[0]);
            }

        });

    }
}



















package com.AisaTest06.view.windows;

import com.AisaTest06.dao.CompanyDaoImpl;
import com.AisaTest06.dao.dao.Interfaces.CompanyDao;
import com.AisaTest06.entity.Company;
import com.AisaTest06.view.components.layouts.MainLayout;
import com.AisaTest06.view.components.textFields.fieldsCompany;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.logging.Logger;

import static com.AisaTest06.view.components.layouts.MainLayout.*;

@SuppressWarnings("ALL")
public class EditCompanyWindow extends Window {

    private static Logger logger = Logger.getLogger(EditCompanyWindow.class.getName());




    public EditCompanyWindow(Company company) {
        setStyleName("Редактировать компанию");
        VerticalLayout editWindowLayout = new VerticalLayout();

        center();
        setClosable(true);
        setDraggable(false);
        setModal(true);
        setContent(editWindowLayout);
        setWidth(400f,Unit.PIXELS);
        setHeight(420,Unit.PIXELS);


        CompanyDao companyDao = new CompanyDaoImpl();




        Button editCompany = new Button("Редактировать");
        editCompany.setSizeFull();
        editCompany.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        editCompany.setIcon(VaadinIcons.EDIT);

        Button cancel = new Button("Отменить",clickEvent -> close());

        cancel.setSizeFull();
        fieldsCompany fieldsCompany = new fieldsCompany();

        TextField nameField = fieldsCompany.getFullNameTextField();
        nameField.setValueChangeMode(ValueChangeMode.EAGER);
        nameField.setSizeFull();
        TextField nipField = fieldsCompany.getNipTextField();
        TextField addressField = fieldsCompany.getAddressTextField();
        TextField phoneField = fieldsCompany.getPhoneTextField();


        final int[] companyidArr = {0};
        final String[] nameArr = {""};
        final String[] NIPArr = {""};
        final String[] addressArr = {""};
        final String[] phoneArr = {""};


        nameField.setRequiredIndicatorVisible(true);
        nipField.setRequiredIndicatorVisible(true);
        addressField.setRequiredIndicatorVisible(true);
        phoneField.setRequiredIndicatorVisible(true);


        companyidArr[0]=company.getCompanyId();
        nameArr[0] =company.getName();
        NIPArr[0] = String.valueOf(company.getNip());
        addressArr[0]=company.getAddress();
        phoneArr[0] = String.valueOf(company.getPhone());


        nameField.setValue(company.getName());
        nipField.setValue(String.valueOf(company.getNip()));
        addressField.setValue(company.getAddress());
        phoneField.setValue(String.valueOf(company.getPhone()));


        nameField.addValueChangeListener(valueChangeEvent ->
                nameArr[0] = valueChangeEvent.getValue()
        );

        nipField.addValueChangeListener(valueChangeEvent ->
                NIPArr[0] = valueChangeEvent.getValue());

        addressField.addValueChangeListener(valueChangeEvent ->
                addressArr[0] = valueChangeEvent.getValue());

        phoneField.addValueChangeListener(valueChangeEvent ->
                phoneArr[0] = valueChangeEvent.getValue());


        editWindowLayout.addComponents( nameField, addressField, nipField, phoneField, editCompany,cancel);


        editCompany.addClickListener((Button.ClickListener) clickEvent15 -> {

            try {

                company.setCompanyId(companyidArr[0]);
                company.setName(nameArr[0]);
                company.setNip(Long.parseLong(NIPArr[0]));
                company.setAddress(addressArr[0]);
                company.setPhone(Long.parseLong(phoneArr[0]));

                if (!(nameArr[0].isEmpty() || NIPArr[0].isEmpty() ||
                        addressArr[0].isEmpty() || phoneArr[0].isEmpty())) {

                    companyDao.editCompany(company);
                    MainLayout.tabSheet.setSelectedTab(tabEmployee);
                    MainLayout.tabSheet.setSelectedTab(tabCompany);
                } else {
                        fieldsCompany.check(nameField);
                        fieldsCompany.check(nipField);
                        fieldsCompany.check(addressField);
                        fieldsCompany.check(phoneField);
                    logger.warning("Неверные данные компании " + company);

                }
            } catch (NumberFormatException ex) {
                logger.warning("Неверная редакция компании " + ex + " "+company);
            } catch (NullPointerException ex) {
                logger.warning("NPE компании " + company + " " + ex);
            }
        });


    }





}


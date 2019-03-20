package com.AisaTest06.view.windows;

import com.AisaTest06.dao.CompanyDaoImpl;
import com.AisaTest06.dao.daoInterfaces.CompanyDao;
import com.AisaTest06.entity.Company;
import com.AisaTest06.view.components.layouts.MainLayout;
import com.AisaTest06.view.components.textFields.TextFieldsCompany;
import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;
import java.util.logging.Logger;

import static com.AisaTest06.view.components.layouts.MainLayout.tab1;
import static com.AisaTest06.view.components.layouts.MainLayout.tab2;

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
        TextFieldsCompany textFieldsCompany = new TextFieldsCompany();

        TextField nameField = textFieldsCompany.getFullName();
        nameField.setValueChangeMode(ValueChangeMode.EAGER);
        nameField.setSizeFull();
        TextField nip = textFieldsCompany.getNip();
        TextField address = textFieldsCompany.getAddress();
        TextField phone = textFieldsCompany.getPhone();


        final int[] companyid = {0};
        final String[] name = {""};
        final String[] NIPArr = {""};
        final String[] addressArr = {""};
        final String[] phoneArr = {""};


        nameField.setRequiredIndicatorVisible(true);
        nip.setRequiredIndicatorVisible(true);
        address.setRequiredIndicatorVisible(true);
        phone.setRequiredIndicatorVisible(true);


        companyid[0]=company.getCompanyId();
        name[0] =company.getName();
        NIPArr[0] = String.valueOf(company.getNip());
        addressArr[0]=company.getAddress();
        phoneArr[0] = String.valueOf(company.getPhone());


        nameField.setValue(company.getName());
        nip.setValue(String.valueOf(company.getNip()));
        address.setValue(company.getAddress());
        phone.setValue(String.valueOf(company.getPhone()));


        nameField.addValueChangeListener(valueChangeEvent ->
                name[0] = valueChangeEvent.getValue()
        );

        nip.addValueChangeListener(valueChangeEvent ->
                NIPArr[0] = valueChangeEvent.getValue());

        address.addValueChangeListener(valueChangeEvent ->
                addressArr[0] = valueChangeEvent.getValue());

        phone.addValueChangeListener(valueChangeEvent ->
                phoneArr[0] = valueChangeEvent.getValue());


        editWindowLayout.addComponents( nameField, address, nip, phone, editCompany,cancel);


        editCompany.addClickListener((Button.ClickListener) clickEvent15 -> {

            try {

                company.setCompanyId(companyid[0]);
                company.setName(name[0]);
                company.setNip(Long.parseLong(NIPArr[0]));
                company.setAddress(addressArr[0]);
                company.setPhone(Long.parseLong(phoneArr[0]));

                if (!(name[0].isEmpty() || NIPArr[0].isEmpty() ||
                        addressArr[0].isEmpty() || phoneArr[0].isEmpty())) {

                    companyDao.editCompany(company);
                    MainLayout.tabSheet.setSelectedTab(tab2);
                    MainLayout.tabSheet.setSelectedTab(tab1);
                } else {
                        textFieldsCompany.check(nameField);
                        textFieldsCompany.check(nip);
                        textFieldsCompany.check(address);
                        textFieldsCompany.check(phone);
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


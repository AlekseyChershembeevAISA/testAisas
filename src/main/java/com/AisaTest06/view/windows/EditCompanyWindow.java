package com.AisaTest06.view.windows;

import com.AisaTest06.dao.CompanyDaoImpl;
import com.AisaTest06.dao.daoInterfaces.CompanyDao;
import com.AisaTest06.entity.Company;
import com.AisaTest06.view.components.textFields.TextFieldsCompany;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;
import java.util.logging.Logger;

public class EditCompanyWindow extends Window {

    private static Logger logger = Logger.getLogger(EditCompanyWindow.class.getName());


    public EditCompanyWindow() {
        setStyleName("Редактировать компанию");
        VerticalLayout editWindowLayout = new VerticalLayout();

        center();
        setClosable(true);
        setDraggable(false);
        setModal(true);
        setContent(editWindowLayout);
        setWidth(400f,Unit.PIXELS);
        setHeight(500f,Unit.PIXELS);


        CompanyDao companyDao = new CompanyDaoImpl();
        List companyList = companyDao.selectAllCompanies();

        ComboBox<Company> selectAllCompanies = new ComboBox<>("Выбрать компанию");
        selectAllCompanies.setSizeFull();


        selectAllCompanies.setItems(companyList);
        selectAllCompanies.setItemCaptionGenerator(Company::getName);
        selectAllCompanies.setEmptySelectionAllowed(false);

        Button editCompany = new Button("Редактировать");
        editCompany.setSizeFull();
        editCompany.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        editCompany.setIcon(VaadinIcons.EDIT);

        Button cancel = new Button("Отменить",clickEvent -> close());

        cancel.setSizeFull();
        TextFieldsCompany textFieldsCompany = new TextFieldsCompany();

        TextField nameField = textFieldsCompany.getFullName();
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

        selectAllCompanies.addValueChangeListener(valueChangeEvent -> {
            companyid[0] = valueChangeEvent.getValue().getCompanyId();
            logger.info("Выбрана компания " + valueChangeEvent.getValue());
        });


        nameField.addValueChangeListener(valueChangeEvent ->
                name[0] = valueChangeEvent.getValue()
        );

        nip.addValueChangeListener(valueChangeEvent ->
                NIPArr[0] = valueChangeEvent.getValue());

        address.addValueChangeListener(valueChangeEvent ->
                addressArr[0] = valueChangeEvent.getValue());

        phone.addValueChangeListener(valueChangeEvent ->
                phoneArr[0] = valueChangeEvent.getValue());


        editWindowLayout.addComponents(selectAllCompanies, nameField, address, nip, phone, editCompany,cancel);


        editCompany.addClickListener((Button.ClickListener) clickEvent15 -> {

            Company company = null;
            try {
                company = new Company(companyid[0], name[0], Long.parseLong(NIPArr[0])
                        , addressArr[0], Long.parseLong(phoneArr[0]));


                if (!(name[0].isEmpty() || NIPArr[0].isEmpty() ||
                        addressArr[0].isEmpty() || phoneArr[0].isEmpty())) {

                    companyDao.editCompany(company);
                } else {

                    logger.warning("Неверные данные сотрудника " + company);

                }
            } catch (NumberFormatException ex) {
                logger.warning("Неверная редакция компании " + ex);
            } catch (NullPointerException ex) {
                logger.warning("NPE компании " + company + " " + ex);
            }
        });


    }


}

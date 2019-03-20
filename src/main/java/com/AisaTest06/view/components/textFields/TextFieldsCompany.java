package com.AisaTest06.view.components.textFields;


import com.AisaTest06.entity.Company;
import com.vaadin.data.Binder;
import com.vaadin.server.UserError;
import com.vaadin.ui.TextField;

import java.util.logging.Logger;

public class TextFieldsCompany extends TextField {

    private static Logger logger = Logger.getLogger(TextFieldsCompany.class.getName());


    private TextField nip;
    private TextField address;
    private TextField phone;
    private TextField fullName;

    final Binder<Company> binder = new Binder<>(Company.class);


    public TextFieldsCompany() {

    }

    public TextField getAddress() {
        address = new TextField("Адрес");
        address.setSizeFull();

        return address;
    }

    public TextField getFullName() {
        fullName = new TextField("Имя компании");
        fullName.setWidth(80, Unit.PERCENTAGE);
        return fullName;
    }

    public TextField getPhone() {
        phone = new TextField("Телефон");
        phone.setSizeFull();
        phone.addValueChangeListener(event -> {
            if (!event.getValue().matches("\\d+")) {
                phone.setComponentError(new UserError("Должны быть цифры"));
            } else {
                phone.setComponentError(null);

            }
        });
        return phone;
    }

    public TextField getNip() {
        nip = new TextField("ИНН");
        nip.setSizeFull();
        nip.addValueChangeListener(event -> {
            if (event.getValue().length() != 12 || !event.getValue().matches("\\d+")) {
                nip.setComponentError(new UserError("Должно быть 12 цифр"));
            } else {
                nip.setComponentError(null);

            }
        });
        return nip;
    }

    public void check(TextField textField) {

        if (textField.getValue().isEmpty()) {
            textField.setComponentError(new UserError("Необходимо заполнить все поля"));
        } else {
            textField.setComponentError(null);
        }


    }
}





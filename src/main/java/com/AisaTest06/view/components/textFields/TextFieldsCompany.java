package com.AisaTest06.view.components.textFields;


import com.AisaTest06.entity.Company;
import com.vaadin.data.Binder;
import com.vaadin.server.UserError;
import com.vaadin.ui.TextField;

import java.util.logging.Logger;

public class TextFieldsCompany extends TextField {
    //прикручиваем сюда все проверки на поля

    private static Logger logger = Logger.getLogger(TextFieldsCompany.class.getName());


    private TextField nip;
    private TextField address;
    private TextField phone;
    private TextField fullName;






    public TextField getAddress() {
        address = new TextField("Адрес");
        return address;
    }

    public TextField getFullName() {
        fullName = new TextField("Имя компании");
        return fullName;
    }

    public TextField getPhone() {
        phone = new TextField("Телефон");
                phone.addValueChangeListener(event -> {
                    if (!event.getValue().matches("\\d+")) {
                        phone.setComponentError(new UserError("Должны быть цифры"));
                    } else {
                        phone.setComponentError(null);

                    }
                });
        return phone;
    }

    public TextField getNip(){
         nip = new TextField("ИНН");
                nip.addValueChangeListener(event -> {
                    if (event.getValue().length() != 12 || !event.getValue().matches("\\d+")) {
                        nip.setComponentError(new UserError("Должно быть 12 цифр"));
                    } else {
                        nip.setComponentError(null);

                    }
                });
        return nip;
    }



    }





package com.AisaTest06.view.components.textFields;


import com.vaadin.server.UserError;
import com.vaadin.ui.TextField;

import java.util.logging.Logger;

public class fieldsCompany extends TextField {

    private static Logger logger = Logger.getLogger(fieldsCompany.class.getName());


    private TextField nipTextField;
    private TextField phoneTextField;




    public fieldsCompany() {

    }

    public TextField getAddressTextField() {
        TextField addressTextField = new TextField("Адрес");
        addressTextField.addValueChangeListener(valueChangeEvent -> {
            if (!(valueChangeEvent.getValue().length()>0)){
                addressTextField.setComponentError(
                        new UserError("Необходимо заполнить поле "+ addressTextField.getCaption()));
            }
            else {
                addressTextField.setComponentError(null);
            }
        });
        addressTextField.setSizeFull();

        return addressTextField;
    }

    public TextField getFullNameTextField() {
        TextField fullNameTextField = new TextField("Имя компании");

        fullNameTextField.addValueChangeListener(valueChangeEvent -> {
            if (!(valueChangeEvent.getValue().length()>0)){
                fullNameTextField.setComponentError(
                        new UserError("Необходимо заполнить поле "+ fullNameTextField.getCaption()));
            }else {
                fullNameTextField.setComponentError(null);
            }
        });
        fullNameTextField.setWidth(80, Unit.PERCENTAGE);
        return fullNameTextField;
    }

    public TextField getPhoneTextField() {
        phoneTextField = new TextField("Телефон");
        phoneTextField.setSizeFull();
        phoneTextField.addValueChangeListener(event -> {
            if (!event.getValue().matches("\\d+")) {
                phoneTextField.setComponentError(
                        new UserError("Должны быть цифры"));
            } else {
                phoneTextField.setComponentError(null);

            }
        });
        return phoneTextField;
    }

    public TextField getNipTextField() {
        nipTextField = new TextField("ИНН");
        nipTextField.setSizeFull();
        nipTextField.addValueChangeListener(event -> {
            if (event.getValue().length() != 12 || !event.getValue().matches("\\d+")) {
                nipTextField.setComponentError(
                        new UserError("Должно быть 12 цифр"));
            } else {
                nipTextField.setComponentError(null);

            }
        });
        return nipTextField;
    }

    public void check(TextField textField) {

        if (textField.getValue().isEmpty()) {
            textField.setComponentError(
                    new UserError("Необходимо заполнить поле "+ textField.getCaption()));
            logger.warning("Пустое поле "+ textField.getCaption());
        } else {
            textField.setComponentError(null);
        }


    }


}





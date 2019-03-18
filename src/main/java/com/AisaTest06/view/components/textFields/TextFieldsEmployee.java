package com.AisaTest06.view.components.textFields;


import com.vaadin.server.UserError;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;

public class TextFieldsEmployee extends TextField {


    private TextField fullName;
    private DateField dateField;
    private TextField email;

    public TextField getFullName() {
        fullName = new TextField("ФИО");
        fullName.setSizeFull();
        return fullName;
    }

    public DateField getDateField() {

        dateField = new DateField("Дата рождения");
        dateField.setSizeFull();


        return dateField;
    }

    public TextField getEmail() {

        email = new TextField("Email");
        email.setSizeFull();
        email.addValueChangeListener(event -> {
            String EMAIL_PATTERN =
                    "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" +
                            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


            if (!event.getValue().matches(EMAIL_PATTERN)) {
                email.setComponentError(new UserError("Нужен email"));
            } else {
                email.setComponentError(null);

            }



        });
        return email;

    }
}

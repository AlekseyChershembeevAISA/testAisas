package com.AisaTest06.view.components.textFields;


import com.vaadin.server.UserError;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;

@SuppressWarnings("ALL")
public class TextFieldsEmployee extends TextField {


    private TextField email;

    public TextField getFullName() {
        TextField fullName = new TextField("ФИО");
        fullName.setSizeFull();
        return fullName;
    }

    public DateField getDateField() {

        DateField dateField = new DateField("Дата рождения");
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

    public void check(TextField textField) {

        if (textField.getValue().isEmpty()) {
            textField.setComponentError(new UserError("Необходимо заполнить все поля"));
        } else {
            textField.setComponentError(null);
        }

    }

    public void check(DateField dateField) {
        if (dateField.isEmpty()) {
            dateField.setComponentError(new UserError("Необходимо заполнить все поля"));
        } else {
            dateField.setComponentError(null);
        }
    }

    public void check(ComboBox comboBox) {
        if (comboBox.isEmpty()) {
            comboBox.setComponentError(new UserError("Необходимо заполнить все поля"));
        } else {
            comboBox.setComponentError(null);
        }
    }

}

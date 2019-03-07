package com.AisaTest06.view;

import com.vaadin.ui.Button;

public class UpButtons extends Button {

    public Button addButton(String name) {

        return new Button("Добавить");
    }

    public Button deleteButton(String name) {

        return new Button("Удалить");
    }

    public Button editButton(String name) {

        return new Button("Редактировать");
    }
}

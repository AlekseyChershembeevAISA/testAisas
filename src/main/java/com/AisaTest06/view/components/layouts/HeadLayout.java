package com.AisaTest06.view.components.layouts;

import com.AisaTest06.view.windows.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

import java.util.logging.Logger;

import static com.AisaTest06.view.components.layouts.MainLayout.*;

public class HeadLayout extends HorizontalLayout {

    private static Logger logger = Logger.getLogger(HeadLayout.class.getName());

    static TextField search;

    HeadLayout() {
        setMargin(false);
        setSpacing(true);


        Button addButton = new Button("Добавить");
        Button deleteButton = new Button("Удалить");
        Button editButton = new Button("Редактировать");
        search = new TextField();

        search.setPlaceholder("поиск");
        search.setSizeFull();


        addComponents(addButton, editButton, deleteButton, search);




        //добавляем новое окошко взависимости от выбранной табы
        addButton.addClickListener(clickEvent -> {

            if (tabSheet.getSelectedTab().equals(tab1)) {

                AddCompanyWindow addComWindow = new AddCompanyWindow();

                UI.getCurrent().addWindow(addComWindow);


            } else if (tabSheet.getSelectedTab().equals(tab2)) {


                AddEmployeeWindow addEmployeeWindow = new AddEmployeeWindow();
                UI.getCurrent().addWindow(addEmployeeWindow);


            }
        });

        // добавляем новое окошко для удаления компании/сотрудника
        deleteButton.addClickListener((Button.ClickListener) clickEvent -> {
            if (tabSheet.getSelectedTab().equals(tab1)) {
                DeleteCompanyWindow deleteComWindow = new DeleteCompanyWindow();
                UI.getCurrent().addWindow(deleteComWindow);


            } else if (tabSheet.getSelectedTab().equals(tab2)) {
                DeleteEmployeeWindow deleteWindow = new DeleteEmployeeWindow();
                UI.getCurrent().addWindow(deleteWindow);

            }
        });

        //добавляем новое окошко для редактирования компании/сотрудника
        editButton.addClickListener((Button.ClickListener) clickEvent -> {
            if (tabSheet.getSelectedTab().equals(tab1)) {
                EditCompanyWindow editComWindow = new EditCompanyWindow();
                UI.getCurrent().addWindow(editComWindow);


            } else if (tabSheet.getSelectedTab().equals(tab2)) {
                EditEmployeeWindow editWindow = new EditEmployeeWindow();
                UI.getCurrent().addWindow(editWindow);

            }
        });


    }


}

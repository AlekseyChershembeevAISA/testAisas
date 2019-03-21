package com.AisaTest06.view.components.layouts;

import com.AisaTest06.view.windows.AddCompanyWindow;
import com.AisaTest06.view.windows.AddEmployeeWindow;
import com.AisaTest06.view.windows.DeleteCompanyWindow;
import com.AisaTest06.view.windows.DeleteEmployeeWindow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.logging.Logger;

import static com.AisaTest06.view.components.layouts.MainLayout.*;

class HeadLayout extends HorizontalLayout {

    private static Logger logger = Logger.getLogger(HeadLayout.class.getName());

    static TextField searchField;

    HeadLayout() {
        setMargin(false);
        setSpacing(true);
        setSizeFull();

        Button addButton = new Button("Добавить");
        addButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
        addButton.setIcon(VaadinIcons.INSERT);
        Button deleteButton = new Button("Удалить");
        deleteButton.setStyleName(ValoTheme.BUTTON_DANGER);
        deleteButton.setIcon(VaadinIcons.MINUS);
       // Button editButton = new Button("Редактировать");
//        editButton.setEnabled(false);
//        editButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
//        editButton.setIcon(VaadinIcons.EDIT);
        searchField = new TextField();

        searchField.setPlaceholder("поиск");

       // hl.addComponents(addButton, editButton, deleteButton);
        HorizontalLayout hl = new HorizontalLayout();
        hl.addComponents(addButton,  deleteButton);


        addComponents(hl, searchField);

        setComponentAlignment(searchField, Alignment.MIDDLE_RIGHT);

        /*Добавляем новое окошко взависимости от выбранной табы при нажатии кнопки Добавить*/
        addButton.addClickListener(clickEvent -> {

            if (tabSheet.getSelectedTab().equals(tabCompany)) {

                AddCompanyWindow addComWindow = new AddCompanyWindow();


                UI.getCurrent().addWindow(addComWindow);


            } else if (tabSheet.getSelectedTab().equals(tabEmployee)) {


                AddEmployeeWindow addEmployeeWindow = new AddEmployeeWindow();
                UI.getCurrent().addWindow(addEmployeeWindow);

            }
        });

         /*
         Добавляем новое окошко для удаления компании/сотрудника при нажатии кнопки Удалить
         */
        deleteButton.addClickListener((Button.ClickListener) clickEvent -> {
            if (tabSheet.getSelectedTab().equals(tabCompany)) {
                DeleteCompanyWindow deleteComWindow = new DeleteCompanyWindow();
                UI.getCurrent().addWindow(deleteComWindow);


            } else if (tabSheet.getSelectedTab().equals(tabEmployee)) {
                DeleteEmployeeWindow deleteWindow = new DeleteEmployeeWindow();
                UI.getCurrent().addWindow(deleteWindow);

            }
        });

        //добавляем новое окошко для редактирования компании/сотрудника
//        editButton.addClickListener((Button.ClickListener) clickEvent -> {
//            if (tabSheet.getSelectedTab().equals(tabCompany)) {
//                EditCompanyWindow editComWindow = new EditCompanyWindow(new Company());
//                UI.getCurrent().addWindow(editComWindow);
//
//
//            } else if (tabSheet.getSelectedTab().equals(tabEmployee)) {
//                EditEmployeeWindow editWindow = new EditEmployeeWindow(new Employee());
//                UI.getCurrent().addWindow(editWindow);
//
//            }
//        });


    }


}

package com.AisaTest06;


import com.AisaTest06.view.components.layouts.MainLayout;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;
import java.util.logging.Logger;

@SuppressWarnings("ALL")
@Theme("mytheme")

@PreserveOnRefresh
public class MainGui extends UI {

    private static Logger logger = Logger.getLogger(MainGui.class.getName());

    @Override
    protected void init(VaadinRequest vaadinRequest) {


        MainLayout mainLayout = new MainLayout();

        String page = vaadinRequest.getPathInfo();

        if (page==null || page.isEmpty()||"/".equals(page)){
            setContent(mainLayout);
            getPage().setTitle("Welcome Aisa test app");
        }else {
            setContent(new Label("Ошибка 404 ! Страница не найдена"));
            getPage().setTitle("Ошибка 404 ! Страница не найдена");
        }

        logger.info("UI загружен");

    }



    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainGui.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}

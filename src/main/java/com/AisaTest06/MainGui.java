package com.AisaTest06;


import com.AisaTest06.dao.CompanyDaoImpl;
import com.AisaTest06.dao.EmployeeDaoImpl;
import com.AisaTest06.dao.daoInterfaces.CompanyDao;
import com.AisaTest06.dao.daoInterfaces.EmployeeDao;
import com.AisaTest06.entity.Company;
import com.AisaTest06.entity.Employee;
import com.AisaTest06.view.components.grids.Grids;
import com.AisaTest06.view.components.layouts.HeadLayout;
import com.AisaTest06.view.components.layouts.MainLayout;
import com.AisaTest06.view.windows.*;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;
import java.util.logging.Logger;

@Theme("mytheme")


public class MainGui extends UI {

    private static Logger logger = Logger.getLogger(MainGui.class.getName());


    @Override
    protected void init(VaadinRequest vaadinRequest) {


        MainLayout mainLayout = new MainLayout();


        setContent(mainLayout);

    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainGui.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}

package com.AisaTest06.view;

import com.AisaTest06.dao.DAO;
import com.AisaTest06.model.Company;
import com.vaadin.data.Binder;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

import java.util.List;

public class CompanyForm extends FormLayout {

//    private DAO dao;
//    private ListDataProvider<Company>dataProvider;
//
//    public CompanyForm(DAO dao) {
//        this.dao = dao;
//        List<Company>companyList = dao.selectAllCompanies();
//        this.dataProvider = DataProvider.ofCollection(companyList);
//
//    }
//
//
//    public CompanyForm getEditForm(Company company, boolean b){
//        removeAllComponents();
//
//        final Binder<Company> binder = new Binder<>(Company.class);
//
//        TextField name = new TextField("Название компании");
//        TextField nip = new TextField("ИНН");
//        TextField address = new TextField("Адрес");
//        TextField phone = new TextField("телефон");
//
//
//        return this;
//    }




}

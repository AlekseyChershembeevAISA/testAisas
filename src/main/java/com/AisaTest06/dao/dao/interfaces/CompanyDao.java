package com.AisaTest06.dao.dao.interfaces;

import com.AisaTest06.entity.Company;


import java.util.List;

@SuppressWarnings("ALL")
public interface CompanyDao {
    /**
    Интерфейс для 
    добавления компании
    **/
    void insertCompany(Company company);
    /**
    Интерфейс для 
    редактирования компании
    **/
    void editCompany(Company company);
    /**
    Интерфейс для 
    удаления компании
    **/
    void deleteCompany(int companyid);
    /**
    Интерфейс для 
    выбора всех компаний
    **/
    List selectAllCompanies();
    /**
    Интерфейс для 
    поиска компании по полю 
    **/
    List<Company> searchAllCompanies(String search);
    /**
    Интерфейс для 
    проверки компании
    **/
    boolean checkCompanyByName(String name);
}

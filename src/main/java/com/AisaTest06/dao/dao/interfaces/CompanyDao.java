package com.AisaTest06.dao.dao.interfaces;

import com.AisaTest06.entity.Company;


import java.util.List;

@SuppressWarnings("ALL")
public interface CompanyDao {

    void insertCompany(Company company);

    void editCompany(Company company);

    //будем удалять по id
    void deleteCompany(int companyid);

    List selectAllCompanies();

    List<Company> searchAllCompanies(String search);

    boolean checkCompanyByName(String name);
}

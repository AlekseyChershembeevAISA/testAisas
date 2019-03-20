package com.AisaTest06.dao.dao.Interfaces;

import com.AisaTest06.entity.Company;


import java.util.List;

@SuppressWarnings("ALL")
public interface CompanyDao {

    Company insertCompany(Company company);

    Company editCompany(Company company);

    //будем удалять по id
    int deleteCompany(int companyid);

    List selectAllCompanies();

    List<Company> searchAllCompanies(String search);

    boolean checkCompanyByName(String name);
}

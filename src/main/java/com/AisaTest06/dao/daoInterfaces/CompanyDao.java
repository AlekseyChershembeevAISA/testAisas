package com.AisaTest06.dao.daoInterfaces;

import com.AisaTest06.entity.Company;


import java.util.List;

public interface CompanyDao {

    Company insertCompany(Company company);

    Company editCompany(Company company);
    //будем удалять по id
    int deleteCompany(int companyid);

    List<Company> selectAllCompanies();

    List<Company>searchAllCompanies(String search);

    boolean checkCompanyByName(String name);
}

package com.AisaTest06.dao.daoInterfaces;

import com.AisaTest06.Entity.Company;


import java.util.List;

public interface CompanyDao {

    Company insertCompany(Company company);

    Company editCompany(Company company);

    int deleteCompany(Company company);

    List<Company> selectAllCompanies();

    List<Company>searchAllCompanies(String search);

    boolean checkCompanyByName(String name);
}

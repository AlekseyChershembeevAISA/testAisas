package com.AisaTest06.dao.row.mappers;

import com.AisaTest06.entity.Company;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int i) throws SQLException {
        Company company = new Company();
        company.setCompanyId(rs.getInt("companyid"));
        company.setName(rs.getString("name"));
        company.setNip(rs.getLong("nip"));
        company.setAddress(rs.getString("address"));
        company.setPhone(rs.getLong("phone"));
        return company;
    }
}

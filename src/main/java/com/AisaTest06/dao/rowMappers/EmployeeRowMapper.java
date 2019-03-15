package com.AisaTest06.dao.rowMappers;

import com.AisaTest06.entity.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int i) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getInt("employeeid"));
        employee.setFullName(rs.getString("fullname"));
        employee.setBirthDate(rs.getString("birthdate"));
        employee.setEmail(rs.getString("email"));
        employee.setCompanyId(rs.getInt("companyid"));
        employee.setNameCompany(rs.getString("namecompany"));
        return employee;
    }
}

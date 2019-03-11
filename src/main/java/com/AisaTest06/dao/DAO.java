package com.AisaTest06.dao;

import com.AisaTest06.model.Company;
import com.AisaTest06.model.Employee;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


public class DAO {


    private NamedParameterJdbcTemplate jdbcTemplate;

    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();

        try {
            ds.setDriverClassName("org.postgresql.Driver");
            ds.setUrl("jdbc:postgresql://localhost/crowd7");
            ds.setUsername("postgres");
            ds.setPassword("123");

            jdbcTemplate = new NamedParameterJdbcTemplate(ds);

        } catch (DataAccessException d){
            System.out.println(d);
        }
        return ds;
    }


    public Employee insertEmployee(Employee employee) {
        String sql = "INSERT INTO EMPLOYEES (fullName, birthDate, email, companyid) " +
                "VALUES(:fullname,:birthdate,:email,:companyid)";

        MapSqlParameterSource parameters = new MapSqlParameterSource();

        try {
            edit(employee, sql, parameters);


        } catch (DataAccessException d) {
            System.out.println(d);
        }

        return employee;
    }

    private void edit(Employee employee, String sql, MapSqlParameterSource parameters) {
        parameters.addValue("fullname", employee.getFullname());
        parameters.addValue("birthdate", employee.getBirthDate());
        parameters.addValue("email", employee.getEmail());
        parameters.addValue("companyid", employee.getCompanyId());

        jdbcTemplate.update(sql, parameters);
    }

    public Company insertCompany(Company company){
        String sql = "INSERT INTO COMPANIES (name,nip,address,phone)" +
                "VALUES(:name,:nip,:address,:phone) ";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        try {
            edit(company, sql, parameters);


        } catch (DataAccessException d) {
            System.out.println(d);
        }

        return company;
    }

    private void edit(Company company, String sql, MapSqlParameterSource parameters) {
        parameters.addValue("name",company.getName());
        parameters.addValue("nip",company.getNip());
        parameters.addValue("address",company.getAddress());
        parameters.addValue("phone",company.getPhone());

        jdbcTemplate.update(sql,parameters);
    }

    public Employee editEmployee(Employee employee) {
        String sql = "UPDATE employees " +
                "SET fullname=:fullname," +
                "birthdate=:birthdate," +
                "email=:email," +
                "companyid=:companyid" +
                " WHERE employeeid=:employeeid";
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        try {
            parameters.addValue("employeeid",employee.getEmployeeId());
            edit(employee, sql, parameters);


        } catch (DataAccessException d) {
            System.out.println(d);
        }

        return employee;
    }


    public Company editCompany(Company company) {
        String sql = "UPDATE companies " +
                "SET name=:name,"+
                "nip=:nip," +
                "address=:address," +
                "phone=:phone " +
                "WHERE companyid=:companyid";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        try {
            parameters.addValue("companyid",company.getCompanyId());
            edit(company, sql, parameters);

        } catch (DataAccessException d) {
            System.out.println(d);
        }

        return company;
    }


    public int deleteEmployee(Employee employee) {
        String sql = "DELETE FROM EMPLOYEES WHERE employeeid=:employeeid";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        int result;

        try {
            parameters.addValue("employeeid", employee.getEmployeeId());

            result = jdbcTemplate.update(sql, parameters);


            return result;
        } catch (DataAccessException d){
            System.out.println(d);
        }

        return employee.getEmployeeId();
    }


    public int deleteCompnay(Company company) {
        String sql = "DELETE FROM COMPANIES WHERE companyid=:companyid";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        int result;

        try {
            parameters.addValue("companyid", company.getCompanyId());

            result = jdbcTemplate.update(sql,parameters);


            return result;
        } catch (DataAccessException d) {
            System.out.println(d);
        }

        return company.getCompanyId();
    }

    public List<Employee> selectAllEmployees() {
        List<Employee> listAllEmployees = new ArrayList<>();
        String sql = "SELECT employees.employeeid,employees.fullname," +
                "employees.birthdate,employees.email,employees.companyid FROM employees";

        try {
            listAllEmployees = jdbcTemplate.query(sql, new EmployeeRowMapper());


        } catch (DataAccessException d) {
            System.out.println(d);
        }

        return listAllEmployees;
    }


    public List<Company> selectAllCompanies() {
        List<Company> listCompanies = new ArrayList<>();
        String sql = "SELECT companyid,name,nip,address,phone FROM companies";

        try {
            listCompanies = jdbcTemplate.query(sql, new CompanyRowMapper());



        } catch (DataAccessException d) {
            System.out.println(d);
        }

        return listCompanies;
    }

    public List<Company>searchAllCompanies(String search){
        List<Company> listCompanies;
        String sql;
        try {
            if (!search.equals(""))
           {

               sql = "select*from companies where " +
                       "name like " +
                       "" +"'%" + search + "%' " +
                       "or cast(nip as varchar) like " +
                       "" +"'%" + search + "%' " +
                       "or address like " +
                       "" +"'%" + search + "%' " +
                       "or cast (phone as varchar) like " +
                       "" +"'%" + search + "%'";
               Company company = new Company();
               company.setCompanyId(company.getCompanyId());
               company.setName(company.getName());
               company.setAddress(company.getAddress());
               company.setNip(company.getNip());
               company.setPhone(company.getPhone());
               listCompanies = jdbcTemplate.query(sql,new CompanyRowMapper());
               return listCompanies;
            }
            else {
               listCompanies =selectAllCompanies();
               return listCompanies;
            }
        }
        catch (DataAccessException d){
            System.out.println(d);
            return null;
        }
    }

    public List<Employee>searchAllEmployees(String search){
        List<Employee> listEmployee;
        String sql;

        try {
            if (!search.equals(""))
            {
                sql = "select*from employees where " +
                        "fullname like " +
                        "" +"'%" + search + "%' " +
                        "or birthdate like " +
                        "" +"'%" + search + "%' " +
                        "or email like " +
                        "" +"'%" + search + "%' ";

                Employee employee = new Employee();
                employee.setEmployeeId(employee.getEmployeeId());
                employee.setFullname(employee.getFullname());
                employee.setEmail(employee.getEmail());
                employee.setBirthDate(employee.getBirthDate());
                employee.setCompanyId(employee.getCompanyId());

                listEmployee= jdbcTemplate.query(sql,new EmployeeRowMapper());
                return listEmployee;
            }
            else {
                listEmployee =selectAllEmployees();
                return listEmployee;
            }

        }
        catch (DataAccessException d){
            System.out.println(d);
            return null;
        }
    }



}


package com.AisaTest06.dao;

import com.AisaTest06.Entity.Employee;
import com.AisaTest06.dao.dataSourceConfig.DataSourceConfiguration;
import com.AisaTest06.dao.rowMappers.EmployeeRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.logging.Logger;

public class EmployeeDaoI implements com.AisaTest06.dao.daoInterfaces.EmployeeDao {

    private static Logger logger = Logger.getLogger(CompanyDaoI.class.getName());

    private NamedParameterJdbcTemplate jdbcTemplate;

    public EmployeeDaoI() {

        this.jdbcTemplate = new NamedParameterJdbcTemplate(DataSourceConfiguration.getInstance());
        logger.info("Успешно подключение к EmployeeDaoI");
    }

    @Override
    public Employee insertEmployee(Employee employee) {
        String sql = "INSERT INTO EMPLOYEES (fullName, birthDate, email, companyid) " +
                "VALUES(:fullname,:birthdate,:email,:companyid)";

        MapSqlParameterSource parameters = new MapSqlParameterSource();

        try {
            edit(employee, sql, parameters);
            logger.info("Успешно добавлен новый сотрудник " + employee.getFullname());
            return employee;

        } catch (DataAccessException d) {
            logger.warning("Ошибка при добавлении нового сотрудника " + d);
            return null;
        }

    }

    private void edit(Employee employee, String sql, MapSqlParameterSource parameters) {
        parameters.addValue("fullname", employee.getFullname());
        parameters.addValue("birthdate", employee.getBirthDate());
        parameters.addValue("email", employee.getEmail());
        parameters.addValue("companyid", employee.getCompanyId());

        jdbcTemplate.update(sql, parameters);
    }


    @Override
    public Employee editEmployee(Employee employee) {
        String sql = "UPDATE employees " +
                "SET fullname=:fullname," +
                "birthdate=:birthdate," +
                "email=:email," +
                "companyid=:companyid" +
                " WHERE employeeid=:employeeid";
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        try {
            parameters.addValue("employeeid", employee.getEmployeeId());
            edit(employee, sql, parameters);
            logger.info("Успешно изменен сотрудник " + employee.getEmployeeId() + " " + employee.getFullname());

            return employee;
        } catch (DataAccessException d) {
            logger.warning("Ошибка при изменении сотрудника " + d);
            return null;
        }

    }

    @Override
    public int deleteEmployee(Employee employee) {
        String sql = "DELETE FROM EMPLOYEES WHERE employeeid=:employeeid";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        int result;

        try {
            parameters.addValue("employeeid", employee.getEmployeeId());

            result = jdbcTemplate.update(sql, parameters);

            logger.info("Успешно удален сотрудник " + employee.getEmployeeId() + " " + employee.getFullname());

            return result;
        } catch (DataAccessException d) {
            logger.warning("Ошибка при удалении сотрудника " + d);
            return 0;
        }


    }

    @Override
    public List<Employee> selectAllEmployees() {
        List<Employee> listAllEmployees;
        String sql = "SELECT employeeid, fullname, birthdate, email, companyid FROM employees";

        try {
            listAllEmployees = jdbcTemplate.query(sql, new EmployeeRowMapper());
            logger.info("Успешно добавлены все сотрудники ");
            return listAllEmployees;

        } catch (DataAccessException d) {
            logger.warning("Ошибка при добавлении сотрудников " + d);
            return null;
        }


    }

    @Override
    public List<Employee> searchAllEmployees(String search) {
        List<Employee> listEmployee;
        String sql;
        try {
            if (!search.equals("")) {
                sql = "select*from employees where " +
                        "fullname like " +
                        "" + "'%" + search + "%' " +
                        "or birthdate like " +
                        "" + "'%" + search + "%' " +
                        "or email like " +
                        "" + "'%" + search + "%' ";

                Employee employee = new Employee();
                employee.setEmployeeId(employee.getEmployeeId());
                employee.setFullname(employee.getFullname());
                employee.setEmail(employee.getEmail());
                employee.setBirthDate(employee.getBirthDate());
                employee.setCompanyId(employee.getCompanyId());

                listEmployee = jdbcTemplate.query(sql, new EmployeeRowMapper());
                logger.info("Успешно найдены искомые сотрудники по строке " + search);
                return listEmployee;
            } else {
                listEmployee = selectAllEmployees();
                return listEmployee;
            }
        } catch (DataAccessException d) {
            logger.warning("Ошибка при поиске сотрудника по строке " + d);
            return null;
        }
    }


}

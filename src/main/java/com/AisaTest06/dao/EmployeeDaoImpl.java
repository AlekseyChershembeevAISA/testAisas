package com.AisaTest06.dao;

import com.AisaTest06.entity.Employee;
import com.AisaTest06.dao.dataSourceConfig.DataSourceConfiguration;
import com.AisaTest06.dao.rowMappers.EmployeeRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.logging.Logger;

public class EmployeeDaoImpl implements com.AisaTest06.dao.daoInterfaces.EmployeeDao {

    private static Logger logger = Logger.getLogger(CompanyDaoImpl.class.getName());

    private NamedParameterJdbcTemplate jdbcTemplate;

    public EmployeeDaoImpl() {

        this.jdbcTemplate = new NamedParameterJdbcTemplate(DataSourceConfiguration.getInstance());
        logger.info("Успешно подключение к EmployeeDaoImpl");
    }

    private static final String DAO_INSERT_EMPLOYEE =
            "INSERT INTO EMPLOYEES " +
            "(fullName, birthDate, email, companyid, namecompany) " +
            "VALUES(:fullname,:birthdate,:email,:companyid,:namecompany)";
    @Override
    public Employee insertEmployee(Employee employee) {


        MapSqlParameterSource parameters = new MapSqlParameterSource();

        try {
            parameters.addValue("fullname", employee.getFullName());
            parameters.addValue("birthdate", employee.getBirthDate());
            parameters.addValue("email", employee.getEmail());
            parameters.addValue("companyid", employee.getCompanyId());
            parameters.addValue("namecompany",employee.getNameCompany());

            jdbcTemplate.update(DAO_INSERT_EMPLOYEE, parameters);

            logger.info("Успешно добавлен новый сотрудник " + employee.getFullName());

            return employee;

        } catch (DataAccessException d) {
            logger.warning("Ошибка при добавлении нового сотрудника " + d);
            return null;
        }

    }


    private static String DAO_EDIT_EMPLOYEE =
            "UPDATE employees " +
                    "SET fullname=:fullname," +
                    "birthdate=:birthdate," +
                    "email=:email," +
                    "companyid=:companyid, " +
                    "namecompany=:namecompany " +
                    "WHERE employeeid=:employeeid";

    @Override
    public Employee editEmployee(Employee employee) {


        MapSqlParameterSource parameters = new MapSqlParameterSource();

        try {
            parameters.addValue("employeeid", employee.getEmployeeId());
            parameters.addValue("fullname", employee.getFullName());
            parameters.addValue("birthdate", employee.getBirthDate());
            parameters.addValue("email", employee.getEmail());
            parameters.addValue("companyid", employee.getCompanyId());
            parameters.addValue("namecompany",employee.getNameCompany());

            jdbcTemplate.update(DAO_EDIT_EMPLOYEE, parameters);

            logger.info("Успешно изменен сотрудник " + employee.getEmployeeId() + " " + employee.getFullName());

            return employee;
        } catch (DataAccessException d) {
            logger.warning("Ошибка при изменении сотрудника " + d);
            return null;
        }

    }

    private static final String DAO_DELETE_EMPLOYEE =
            "DELETE FROM EMPLOYEES " +
                    "WHERE employeeid=:employeeid";

    @Override
    public int deleteEmployee(int employeeid) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        int result;

        try {
            parameters.addValue("employeeid", employeeid);

            result = jdbcTemplate.update(DAO_DELETE_EMPLOYEE, parameters);

            logger.info("Успешно удален сотрудник " + employeeid + " " );

            return result;
        } catch (DataAccessException d) {
            logger.warning("Ошибка при удалении сотрудника " + d);
            return 0;
        }


    }

    private static final String DAO_SELECT_ALL_EMPLOYEES =
            "SELECT employeeid, fullname, birthdate, email, companyid, namecompany" +
                    " FROM employees";

    @Override
    public List<Employee> selectAllEmployees() {
        List<Employee> listAllEmployees;


        try {
            listAllEmployees = jdbcTemplate.query(DAO_SELECT_ALL_EMPLOYEES, new EmployeeRowMapper());
            logger.info("Успешно добавлены все сотрудники ");
            return listAllEmployees;

        } catch (DataAccessException d) {
            logger.warning("Ошибка при добавлении сотрудников " + d);
            return null;
        }


    }

    private static String DAO_SEARCH_ALL_EMPLOYEES(String search){
        String filterLike = "" + "'%" + search + "%' ";

        return "select*from employees where " +
                "fullname like " +
                filterLike +
                "or birthdate like " +
                filterLike +
                "or email like " +
                filterLike +
                "or namecompany like " +
                filterLike;
    }


    @Override
    public List<Employee> searchAllEmployees(String search) {
        List<Employee> listEmployee;

        try {
            if (!search.equals("")) {

                Employee employee = new Employee();
                employee.setEmployeeId(employee.getEmployeeId());
                employee.setFullName(employee.getFullName());
                employee.setEmail(employee.getEmail());
                employee.setBirthDate(employee.getBirthDate());
                employee.setCompanyId(employee.getCompanyId());
                employee.setNameCompany(employee.getNameCompany());


                listEmployee = jdbcTemplate.query(DAO_SEARCH_ALL_EMPLOYEES(search), new EmployeeRowMapper());

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

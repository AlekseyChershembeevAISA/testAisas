package com.AisaTest06.dao;

import com.AisaTest06.Entity.Company;
import com.AisaTest06.Entity.Employee;
import com.AisaTest06.dao.dataSourceConfig.DataSourceConfiguration;
import com.AisaTest06.dao.rowMappers.CompanyRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.logging.Logger;

public class CompanyDao implements com.AisaTest06.dao.daoInterfaces.CompanyDao {

    private static Logger logger = Logger.getLogger(CompanyDao.class.getName());

    private NamedParameterJdbcTemplate jdbcTemplate;

    public CompanyDao() {

        this.jdbcTemplate = new NamedParameterJdbcTemplate(DataSourceConfiguration.getInstance());
        logger.info("Успешно подключение к CompanyDao");
    }





    @Override
    public Company insertCompany(Company company){
        String sql = "INSERT INTO COMPANIES (name,nip,address,phone)" +
                "VALUES(:name,:nip,:address,:phone) ";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        try {

            edit(company, sql, parameters);
            logger.info("Успешно добавлена новая компания "+ company.getCompanyId()+ " "+company.getName());
            return company;

        } catch (DataAccessException d) {
            logger.warning("Ошибка при добавлении новой компании "+ d);
            return null;
        }


    }

    private void edit(Company company, String sql, MapSqlParameterSource parameters) {
        parameters.addValue("name",company.getName());
        parameters.addValue("nip",company.getNip());
        parameters.addValue("address",company.getAddress());
        parameters.addValue("phone",company.getPhone());

        jdbcTemplate.update(sql,parameters);
    }


    @Override
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
            logger.info("Успешно изменена компания "+ company.getCompanyId()+" "+ company.getName());
            return company;

        } catch(DataAccessException d) {
            System.out.println("Ошибка при изменении компании "+d);
            return null;
        }

    }



    @Override
    public int deleteCompany(Company company) {
        String sql = "DELETE FROM COMPANIES WHERE companyid=:companyid";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        int result;

        try {
            parameters.addValue("companyid", company.getCompanyId());

            result = jdbcTemplate.update(sql,parameters);

            logger.info("Успешно удалена компания "+ company.getCompanyId()+ " "+ company.getName());

            return result;
        } catch (DataAccessException d) {
            logger.warning("Ошибка при удалении компании "+d);
            return company.getCompanyId();
        }

    }



    @Override
    public List<Company> selectAllCompanies() {
        List<Company> listCompanies;
        String sql = "SELECT companyid,name,nip,address,phone FROM companies";

        try {
            listCompanies = jdbcTemplate.query(sql, new CompanyRowMapper());
            logger.info("Успешно добавлены все компании ");
            return listCompanies;


        } catch (DataAccessException d) {
            logger.warning("Ошибка при добавлении компаний "+ d);
            return null;
        }


    }
    @Override
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
                logger.info("Успешно найдены искомые компании по строке "+ search);
                return listCompanies;
            }
            else {
                listCompanies =selectAllCompanies();
                return listCompanies;
            }
        }
        catch (DataAccessException d){
            logger.warning("Ошибка при поиске компании по строке "+d);
            return null;
        }
    }

    @Override
    public boolean checkCompanyByName(String name){

        MapSqlParameterSource parametres = new MapSqlParameterSource();
        parametres.addValue("name",name);

        try {
            String sql = "select count(name) from companies where name = :name";

            Integer count = jdbcTemplate.queryForObject(sql,parametres,Integer.class);

            if (count>0){
                return  true;}
            else {return false;}
        }
        catch (DataAccessException ex){
            logger.warning("Ошибка при проверке компании по имени "+ ex);
            return false;
        }

    }
}

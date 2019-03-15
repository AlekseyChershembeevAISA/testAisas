package com.AisaTest06.dao;

import com.AisaTest06.entity.Company;
import com.AisaTest06.dao.dataSourceConfig.DataSourceConfiguration;
import com.AisaTest06.dao.rowMappers.CompanyRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.logging.Logger;

public class CompanyDaoImpl implements com.AisaTest06.dao.daoInterfaces.CompanyDao {

    private static Logger logger = Logger.getLogger(CompanyDaoImpl.class.getName());

    private NamedParameterJdbcTemplate jdbcTemplate;

    public CompanyDaoImpl() {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(DataSourceConfiguration.getInstance());
        logger.info("Успешно подключение к CompanyDaoImpl");
    }

    private static final String DAO_INSERT_COMPANY =
            "INSERT INTO COMPANIES " +
                    "(name,nip,address,phone)" +
                    "VALUES(:name,:nip,:address,:phone) ";

    @Override
    public Company insertCompany(Company company) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        try {
            parameters.addValue("name", company.getName());
            parameters.addValue("nip", company.getNip());
            parameters.addValue("address", company.getAddress());
            parameters.addValue("phone", company.getPhone());


            jdbcTemplate.update(DAO_INSERT_COMPANY, parameters);

            logger.info("Успешно добавлена новая компания " + company.getCompanyId() + " " + company.getName());
            return company;

        } catch (DataAccessException d) {
            logger.warning("Ошибка при добавлении новой компании " + d);
            return null;
        }
    }


    private static final String DAO_EDIT_COMPANY =
            "UPDATE companies " +
                    "SET name=:name," +
                    "nip=:nip," +
                    "address=:address," +
                    "phone=:phone " +
                    "WHERE companyid=:companyid";

    @Override
    public Company editCompany(Company company) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        try {
            parameters.addValue("companyid", company.getCompanyId());

            parameters.addValue("name", company.getName());
            parameters.addValue("nip", company.getNip());
            parameters.addValue("address", company.getAddress());
            parameters.addValue("phone", company.getPhone());

            jdbcTemplate.update(DAO_EDIT_COMPANY, parameters);

            logger.info("Успешно изменена компания " + company.getCompanyId() + " " + company.getName());
            return company;

        } catch (DataAccessException d) {
            System.out.println("Ошибка при изменении компании " + d);
            return null;

        }


    }

    private static final String DAO_DELETE_COMPANY =
            "DELETE FROM COMPANIES " +
                    "WHERE companyid=:companyid";

    @Override
    public int deleteCompany(int companyid) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        int result;

        try {
            parameters.addValue("companyid", companyid);

            result = jdbcTemplate.update(DAO_DELETE_COMPANY, parameters);

            logger.info("Успешно удалена компания " + companyid + " " + companyid);

            return result;
        } catch (DataAccessException d) {
            logger.warning("Ошибка при удалении компании " + d);
            return 0;
        }

    }

    private static final String DAO_SELECT_ALL_COMPANIES =
            "SELECT companyid,name,nip,address,phone " +
                    "FROM companies";

    @Override
    public List<Company> selectAllCompanies() {
        List<Company> listCompanies;


        try {
            listCompanies = jdbcTemplate.query(DAO_SELECT_ALL_COMPANIES, new CompanyRowMapper());

            logger.info("Успешно добавлены все компании ");
            return listCompanies;


        } catch (DataAccessException d) {
            logger.warning("Ошибка при добавлении компаний " + d);
            return null;
        }


    }

    ;

    private static String DAO_SEARCH_ALL_COMPANIES(String search){
        String filterLike = "" + "'%" + search + "%' ";

        return "select*from companies where " +
                "name like " +
                filterLike +
                "or cast(nip as varchar) like " +
                filterLike +
                "or address like " +
                filterLike +
                "or cast (phone as varchar) like " +
                filterLike;
    }

    @Override
    public List<Company> searchAllCompanies(String search) {
        List<Company> listCompanies;

        try {
            if (!search.equals("")) {

                Company company = new Company();
                company.setCompanyId(company.getCompanyId());
                company.setName(company.getName());
                company.setAddress(company.getAddress());
                company.setNip(company.getNip());
                company.setPhone(company.getPhone());
                listCompanies = jdbcTemplate.query(DAO_SEARCH_ALL_COMPANIES(search), new CompanyRowMapper());

                logger.info("Успешно найдены искомые компании по строке " + search);
                return listCompanies;
            } else {
                listCompanies = selectAllCompanies();
                logger.warning("Нет текста в поиске, возвращаем все компании");
                return listCompanies;
            }
        } catch (DataAccessException d) {
            logger.warning("Ошибка при поиске компании по строке " + d);
            return null;
        }
    }

    private static final String DAO_CHECK_COMPANY_BY_NAME =
            "select count(name) " +
                    "from companies" +
                    " where name = :name";

    @Override
    public boolean checkCompanyByName(String name) {

        MapSqlParameterSource parametres = new MapSqlParameterSource();
        parametres.addValue("name", name);

        try {

            Integer count = jdbcTemplate.queryForObject(DAO_CHECK_COMPANY_BY_NAME, parametres, Integer.class);

            if (count > 0) {
                return true;
            } else {
                return false;
            }
        }
        catch (DataAccessException ex) {
            logger.warning("Ошибка при проверке компании по имени " + ex);
            return false;
        }


    }
}

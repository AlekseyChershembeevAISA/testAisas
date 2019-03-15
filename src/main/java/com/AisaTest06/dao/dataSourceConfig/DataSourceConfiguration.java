package com.AisaTest06.dao.dataSourceConfig;


import org.apache.commons.dbcp.BasicDataSource;

import java.util.logging.Logger;

public class DataSourceConfiguration {

    public DataSourceConfiguration() {

        logger.info("jdbc:postgresql://localhost/crowd9");
    }

    private static BasicDataSource dataSource;

    private static Logger logger = Logger.getLogger(DataSourceConfiguration.class.getName());

    private static BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost/crowd9");
        dataSource.setUsername("postgres");
        dataSource.setPassword("123");
        return dataSource;
    }

    public static synchronized BasicDataSource getInstance() {
        if (dataSource == null) {
            dataSource = dataSource();
            return dataSource;
        }
        return dataSource;
    }
}




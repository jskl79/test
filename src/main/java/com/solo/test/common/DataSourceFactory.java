package com.solo.test.common;

import com.solo.test.domain.Client;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DataSourceFactory {

    public DataSource createDataSource(Client client) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(client.getDriver());
        dataSource.setUrl(client.getUrl());
        dataSource.setUsername(client.getUsername());
        dataSource.setPassword(client.getPassword());
        return dataSource;
    }


}

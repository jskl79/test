package com.solo.test.config;

import com.solo.test.common.DataSourceFactory;
import com.solo.test.domain.Client;
import com.solo.test.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiRoutingDataSource extends AbstractRoutingDataSource {

    public MultiRoutingDataSource() {
    }

    @Override
    protected Object determineCurrentLookupKey() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String clientId = (String) request.getHeader("clientId");
        System.out.println("clientId=" + clientId);
        return clientId;

    }

//    /**
//     * 클라이언트별 DataSource 조회
//     */
//    public void initDataSources() {
//
//        Map<Object, Object> dsMap = new HashMap<Object, Object>();
//
//        DriverManagerDataSource dataSource1 = new DriverManagerDataSource();
//
//        // See: datasouce-cfg.properties
//        dataSource1.setDriverClassName(env.getProperty("spring.datasource.driver-class-name.1"));
//        dataSource1.setUrl(env.getProperty("spring.datasource.url.1"));
//        dataSource1.setUsername(env.getProperty("spring.datasource.username.1"));
//        dataSource1.setPassword(env.getProperty("spring.datasource.password.1"));
//
//        dsMap.put("01", dataSource1);
//
//        DriverManagerDataSource dataSource2 = new DriverManagerDataSource();
//
//        // See: datasouce-cfg.properties
//        dataSource2.setDriverClassName(env.getProperty("spring.datasource.driver-class-name.2"));
//        dataSource2.setUrl(env.getProperty("spring.datasource.url.2"));
//        dataSource2.setUsername(env.getProperty("spring.datasource.username.2"));
//        dataSource2.setPassword(env.getProperty("spring.datasource.password.2"));
//
//        dsMap.put("02", dataSource2);
//
//        this.setTargetDataSources(dsMap);
//    }


}

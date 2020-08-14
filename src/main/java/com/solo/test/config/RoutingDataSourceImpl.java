package com.solo.test.config;

import com.solo.test.common.DataSourceFactory;
import com.solo.test.domain.Client;
import com.solo.test.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoutingDataSourceImpl extends AbstractRoutingDataSource {

    @Autowired
    private DataSourceFactory dataSourceFactory;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    protected Object determineCurrentLookupKey() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String clientId = (String) request.getHeader("clientId");
        System.out.println("clientId=" + clientId);
        return clientId;

    }

    /**
     * 클라이언트별 DataSource 조회
     */
    public void initDataSources() {

        Map<Object, Object> dsMap = new HashMap<Object, Object>();
        List<Client> clients = clientRepository.findAll();

        for (Client client : clients) {
            dsMap.put(client.getName(), dataSourceFactory.createDataSource(client));
        }

        this.setTargetDataSources(dsMap);
    }


}

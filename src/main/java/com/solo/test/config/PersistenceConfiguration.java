package com.solo.test.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.solo.test",
        entityManagerFactoryRef = "multiEntityManager",
        transactionManagerRef = "multiTransactionManager"
)
public class PersistenceConfiguration {
    private final String PACKAGE_SCAN = "com.solo.test";

    @Bean(name = "clientADataSource")
    @ConfigurationProperties("app.datasource.client1")
    public DataSource clientADataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
    @Bean(name = "clientBDataSource")
    @ConfigurationProperties("app.datasource.client2")
    public DataSource clientBDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
    @Bean(name = "multiRoutingDataSource")
    public DataSource multiRoutingDataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("01", clientADataSource());
        targetDataSources.put("02", clientBDataSource());
        MultiRoutingDataSource multiRoutingDataSource = new MultiRoutingDataSource();
        multiRoutingDataSource.setTargetDataSources(targetDataSources);
        return multiRoutingDataSource;
    }
    @Bean(name = "multiEntityManager")
    public LocalContainerEntityManagerFactoryBean multiEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(multiRoutingDataSource());
        em.setPackagesToScan(PACKAGE_SCAN);
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());
        return em;
    }
    @Bean(name = "multiTransactionManager")
    public PlatformTransactionManager multiTransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                multiEntityManager().getObject());
        return transactionManager;
    }
    @Primary
    @Bean(name = "dbSessionFactory")
    public LocalSessionFactoryBean dbSessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(multiRoutingDataSource());
        sessionFactoryBean.setPackagesToScan(PACKAGE_SCAN);
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        return sessionFactoryBean;
    }
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.format_sql", true);
        return properties;
    }
}
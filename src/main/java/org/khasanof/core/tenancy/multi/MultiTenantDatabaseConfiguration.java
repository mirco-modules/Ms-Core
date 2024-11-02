package org.khasanof.core.tenancy.multi;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.khasanof.core.tenancy.multi.condition.MultiTenantCondition;
import org.khasanof.core.tenancy.multi.database.CreateDatabaseService;
import org.khasanof.core.tenancy.multi.database.CreateDatabaseServiceImpl;
import org.khasanof.core.tenancy.multi.helper.DatabaseNameHelper;
import org.khasanof.core.tenancy.multi.liquibase.LiquibaseService;
import org.khasanof.core.tenancy.multi.liquibase.LiquibaseServiceImpl;
import org.khasanof.core.tenancy.multi.manager.DataSourceManager;
import org.khasanof.core.tenancy.multi.manager.DataSourceManagerImpl;
import org.khasanof.core.tenancy.multi.provider.MultiTenantConnectorProviderImpl;
import org.khasanof.core.tenancy.multi.resolver.TenantIdentifierResolver;
import org.khasanof.core.tenancy.multi.resolver.datasource.DataSourceResolver;
import org.khasanof.core.tenancy.multi.resolver.datasource.DataSourceResolverImpl;
import org.khasanof.core.tenancy.multi.resolver.datasource.MultiTenantDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.core
 * @since 11/2/2024 3:27 PM
 */
@Configuration
@Conditional({MultiTenantCondition.class})
public class MultiTenantDatabaseConfiguration {

    @Autowired
    private Environment environment;

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Autowired
    private DatabaseNameHelper databaseNameHelper;

    private static final String CHANGE_LOG_PATH = "config/liquibase/master.xml";

    @Bean
    public DataSource dataSource() {
        DataSourceManager dataSourceManager = dataSourceManager();
        AbstractRoutingDataSource dataSource = new MultiTenantDataSource(dataSourceManager, tenantIdentifierResolver());
        dataSource.setDefaultTargetDataSource(defaultDataSource());
        dataSource.setTargetDataSources(dataSourceManager.getTargetDataSource());
        dataSource.afterPropertiesSet();
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        Map<String, Object> jpaPropertiesMap = new HashMap<>(jpaProperties.getProperties());
        jpaPropertiesMap.put(AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider());
        jpaPropertiesMap.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, tenantIdentifierResolver());

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("org.khasanof.*");
        em.setJpaVendorAdapter(this.jpaVendorAdapter());
        em.setJpaPropertyMap(jpaPropertiesMap);
        return em;
    }

    /**
     *
     * @return
     */
    @Bean
    public CreateDatabaseService createDatabaseService() {
        return new CreateDatabaseServiceImpl(defaultDataSource());
    }

    @Bean
    public DataSourceManager dataSourceManager() {
        return new DataSourceManagerImpl(liquibaseService(), dataSourceResolver(), databaseNameHelper, createDatabaseService());
    }

    @Bean

    public LiquibaseService liquibaseService() {
        return new LiquibaseServiceImpl(CHANGE_LOG_PATH, dataSourceResolver());
    }

    /**
     *
     * @return
     */
    @Bean
    public TenantIdentifierResolver tenantIdentifierResolver() {
        return new TenantIdentifierResolver();
    }

    /**
     *
     * @return
     */
    @Bean
    public DataSourceResolver dataSourceResolver() {
        return new DataSourceResolverImpl(databaseNameHelper, dataSourceProperties);
    }

    /**
     *
     * @return
     */
    @Bean
    public MultiTenantConnectionProvider<Long> multiTenantConnectionProvider() {
        return new MultiTenantConnectorProviderImpl(dataSource());
    }

    /**
     *
     * @return {@link DataSource}
     */
    public DataSource defaultDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = dataSourceProperties.initializeDataSourceBuilder();
        return dataSourceBuilder.build();
    }
}

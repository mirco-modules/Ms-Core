package org.khasanof.core.tenancy.multi;

import org.khasanof.core.tenancy.multi.condition.MultiTenantCondition;
import org.khasanof.core.tenancy.multi.database.CreateDatabaseService;
import org.khasanof.core.tenancy.multi.database.CreateDatabaseServiceImpl;
import org.khasanof.core.tenancy.multi.helper.DatabaseNameHelper;
import org.khasanof.core.tenancy.multi.liquibase.LiquibaseService;
import org.khasanof.core.tenancy.multi.liquibase.LiquibaseServiceImpl;
import org.khasanof.core.tenancy.multi.liquibase.resolver.LiquibaseChangelogResolver;
import org.khasanof.core.tenancy.multi.liquibase.resolver.LiquibaseChangelogResolverImpl;
import org.khasanof.core.tenancy.multi.manager.DataSourceManager;
import org.khasanof.core.tenancy.multi.manager.DataSourceManagerImpl;
import org.khasanof.core.tenancy.multi.resolver.TenantIdentifierResolver;
import org.khasanof.core.tenancy.multi.resolver.datasource.DataSourceResolver;
import org.khasanof.core.tenancy.multi.resolver.datasource.DataSourceResolverImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.Collections;

/**
 * @author Nurislom
 * @see org.khasanof.core
 * @since 11/2/2024 3:27 PM
 */
@Configuration
@Conditional({MultiTenantCondition.class})
public class MultiTenantDatabaseConfiguration {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Autowired
    private DatabaseNameHelper databaseNameHelper;

    /**
     *
     * @return
     */
    @Bean
    public DataSource dataSource() {
        return new LazyConnectionDataSourceProxy(multitenantDataSource());
    }

    /**
     *
     * @return
     */
    @Bean
    public DataSource multitenantDataSource() {
        DataSourceManager dataSourceManager = dataSourceManager();

        var multiTenantDataSource = new MultiTenantDataSource(defaultDataSource(), dataSourceManager, tenantIdentifierResolver());
        multiTenantDataSource.setDefaultTargetDataSource(defaultDataSource());
        multiTenantDataSource.setTargetDataSources(Collections.emptyMap());
        multiTenantDataSource.afterPropertiesSet();

        return multiTenantDataSource;
    }

    /**
     *
     * @return
     */
    @Bean
    public CreateDatabaseService createDatabaseService() {
        return new CreateDatabaseServiceImpl(defaultDataSource());
    }

    /**
     *
     * @return
     */
    @Bean
    public DataSourceManager dataSourceManager() {
        return new DataSourceManagerImpl(liquibaseService(), dataSourceResolver(), databaseNameHelper, createDatabaseService());
    }

    /**
     *
     * @return
     */
    @Bean
    public LiquibaseService liquibaseService() {
        return new LiquibaseServiceImpl(dataSourceResolver(), liquibaseChangelogResolver());
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
     * @return {@link DataSource}
     */
    DataSource defaultDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = dataSourceProperties.initializeDataSourceBuilder();
        return dataSourceBuilder.build();
    }

    /**
     *
     * @return
     */
    LiquibaseChangelogResolver liquibaseChangelogResolver() {
        return new LiquibaseChangelogResolverImpl();
    }
}

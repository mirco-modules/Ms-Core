package org.khasanof.core.tenancy.multi.manager;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.khasanof.core.migration.core.database.DatabaseCreatorService;
import org.khasanof.core.tenancy.core.model.TenantDataSource;
import org.khasanof.core.tenancy.multi.liquibase.LiquibaseService;
import org.khasanof.core.tenancy.multi.resolver.datasource.DataSourceResolver;
import org.khasanof.core.tenancy.multi.resolver.datasource.url.DataSourceUrlResolver;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.multi.manager
 * @since 11/2/2024 6:30 PM
 */
public class DataSourceManagerImpl implements DataSourceManager {

    private final LiquibaseService liquibaseService;
    private final DataSourceResolver dataSourceResolver;
    private final DataSourceProperties dataSourceProperties;
    private final DataSourceUrlResolver dataSourceUrlResolver;
    private final DatabaseCreatorService databaseCreatorService;

    public DataSourceManagerImpl(LiquibaseService liquibaseService,
                                 DataSourceResolver dataSourceResolver,
                                 DataSourceProperties dataSourceProperties,
                                 DataSourceUrlResolver dataSourceUrlResolver,
                                 DatabaseCreatorService databaseCreatorService) {

        this.liquibaseService = liquibaseService;
        this.dataSourceResolver = dataSourceResolver;
        this.dataSourceProperties = dataSourceProperties;
        this.dataSourceUrlResolver = dataSourceUrlResolver;
        this.databaseCreatorService = databaseCreatorService;
    }

    /**
     *
     * @param tenantIdentifier
     * @return
     */
    @Override
    public DataSource getOrCreate(Long tenantIdentifier) {
        var tenantDataSource = dataSourceResolver.getOrCreate(tenantIdentifier);
        if (Objects.equals(tenantDataSource.getMigrated(), Boolean.TRUE)) {
            return tenantDataSource.getDataSource();
        }

        startMigration(tenantIdentifier, tenantDataSource);
        return tenantDataSource.getDataSource();
    }

    /**
     *
     * @param tenantIdentifier
     * @param tenantDataSource
     */
    private void startMigration(Long tenantIdentifier, TenantDataSource tenantDataSource) {
        tenantDataSource.changeMigratedState();
        createDatabase(tenantIdentifier);
        liquibaseService.migrate(tenantIdentifier);
    }

    /**
     *
     * @param tenantIdentifier
     */
    private void createDatabase(Long tenantIdentifier) {
        var dataSourceProperties = toDataSourceProperties(tenantIdentifier);
        databaseCreatorService.createIfNotExist(dataSourceProperties);
    }

    /**
     *
     * @param tenantIdentifier
     * @return
     */
    private DataSourceProperties toDataSourceProperties(Long tenantIdentifier) {
        DataSourceProperties dataSourceProperties = new DataSourceProperties();

        String targetUrl = dataSourceUrlResolver.resolve(tenantIdentifier, this.dataSourceProperties.getUrl());

        dataSourceProperties.setUrl(targetUrl);
        dataSourceProperties.setType(this.dataSourceProperties.getType());
        dataSourceProperties.setPassword(this.dataSourceProperties.getPassword());
        dataSourceProperties.setUsername(this.dataSourceProperties.getUsername());
        dataSourceProperties.setDriverClassName(this.dataSourceProperties.getDriverClassName());

        return dataSourceProperties;
    }

    /**
     *
     * @param tenantIdentifier
     * @return
     */
    @Override
    public DataSource get(Long tenantIdentifier) {
        var dataSource = dataSourceResolver.getOrCreate(tenantIdentifier);
        return dataSource.getDataSource();
    }

    /**
     *
     * @param tenantIdentifier
     * @return
     */
    @Override
    public boolean existDataSource(Long tenantIdentifier) {
        return dataSourceResolver.existDataSource(tenantIdentifier);
    }

    /**
     *
     * @return
     */
    @Override
    public Map<Long, DataSource> getAllDataSources() {
        return dataSourceResolver.getResolvedDataSources();
    }
}

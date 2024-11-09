package org.khasanof.core.tenancy.multi.manager;

import org.khasanof.core.tenancy.core.model.TenantDataSource;
import org.khasanof.core.tenancy.multi.database.CreateDatabaseService;
import org.khasanof.core.tenancy.multi.helper.DatabaseNameHelper;
import org.khasanof.core.tenancy.multi.liquibase.LiquibaseService;
import org.khasanof.core.tenancy.multi.resolver.datasource.DataSourceResolver;

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
    private final DatabaseNameHelper databaseNameHelper;
    private final CreateDatabaseService createDatabaseService;

    public DataSourceManagerImpl(LiquibaseService liquibaseService,
                                 DataSourceResolver dataSourceResolver,
                                 DatabaseNameHelper databaseNameHelper,
                                 CreateDatabaseService createDatabaseService) {

        this.liquibaseService = liquibaseService;
        this.dataSourceResolver = dataSourceResolver;
        this.databaseNameHelper = databaseNameHelper;
        this.createDatabaseService = createDatabaseService;
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
        var databaseName = databaseNameHelper.getDatabaseName(tenantIdentifier);
        createDatabaseService.create(databaseName);
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

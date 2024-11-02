package org.khasanof.core.tenancy.multi.manager;

import org.khasanof.core.tenancy.core.model.SDataSource;
import org.khasanof.core.tenancy.multi.database.CreateDatabaseService;
import org.khasanof.core.tenancy.multi.helper.DatabaseNameHelper;
import org.khasanof.core.tenancy.multi.liquibase.LiquibaseService;
import org.khasanof.core.tenancy.multi.resolver.datasource.DataSourceResolver;

import javax.sql.DataSource;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.multi.manager
 * @since 11/2/2024 6:30 PM
 */
public class DataSourceManagerImpl implements DataSourceManager {

    private final Map<Object, Object> targetDataSources = new HashMap<>();

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
        SDataSource sDataSource = dataSourceResolver.getOrCreate(tenantIdentifier);
        if (Objects.equals(sDataSource.getIsMigrated(), Boolean.TRUE)) {
            return sDataSource.getDataSource();
        }

        sDataSource.setIsMigrated(true);
        DataSource dataSource = sDataSource.getDataSource();
        targetDataSources.put(tenantIdentifier, dataSource);

        createDatabase(tenantIdentifier);
        liquibaseService.singleTenantMigration(tenantIdentifier);
        return dataSource;
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

    private void createDatabase(Long tenantIdentifier) {
        String databaseName = databaseNameHelper.getDatabaseName(tenantIdentifier);
        createDatabaseService.create(databaseName);
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
        return dataSourceResolver.getAllDataSources().entrySet()
                .stream()
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue().getDataSource()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     *
     * @return
     */
    @Override
    public Map<Object, Object> getTargetDataSource() {
        return this.targetDataSources;
    }
}

package org.khasanof.core.tenancy.multi.liquibase;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.khasanof.core.config.RootDataSourceProperties;
import org.khasanof.core.domain.enumeration.DatabaseType;
import org.khasanof.core.migration.core.database.strategy.manager.DatabaseCreatorStrategyManager;
import org.khasanof.core.tenancy.core.model.TenantDataSource;
import org.khasanof.core.tenancy.multi.liquibase.resolver.LiquibaseChangelogResolver;
import org.khasanof.core.tenancy.multi.resolver.datasource.DataSourceResolver;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.multi.liquibase
 * @since 11/2/2024 6:10 PM
 */
public class LiquibaseServiceImpl implements LiquibaseService {

    private final DataSourceResolver dataSourceResolver;
    private final RootDataSourceProperties rootDataSourceProperties;
    private final LiquibaseChangelogResolver liquibaseChangelogResolver;
    private final DatabaseCreatorStrategyManager databaseCreatorStrategyManager;

    public LiquibaseServiceImpl(DataSourceResolver dataSourceResolver,
                                RootDataSourceProperties rootDataSourceProperties,
                                LiquibaseChangelogResolver liquibaseChangelogResolver,
                                DatabaseCreatorStrategyManager databaseCreatorStrategyManager
    ) {
        this.dataSourceResolver = dataSourceResolver;
        this.rootDataSourceProperties = rootDataSourceProperties;
        this.liquibaseChangelogResolver = liquibaseChangelogResolver;
        this.databaseCreatorStrategyManager = databaseCreatorStrategyManager;
    }

    /**
     *
     */
    @Override
    public void allTenantsMigrations() {
        Map<Long, TenantDataSource> allDataSources = dataSourceResolver.getDataSources();
        allDataSources.keySet().forEach(this::migrate);
    }

    /**
     * @param tenantIdentifier
     */
    @Override
    public void migrate(Long tenantIdentifier) {
        tryExecuteLiquibaseMigration(tenantIdentifier);
    }

    /**
     * @param tenantIdentifier
     */
    private void tryExecuteLiquibaseMigration(Long tenantIdentifier) {
        try {
            Liquibase liquibase = getLiquibase(tenantIdentifier);
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (SQLException | LiquibaseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param tenantIdentifier
     * @return
     * @throws DatabaseException
     * @throws SQLException
     */
    private Liquibase getLiquibase(Long tenantIdentifier) throws DatabaseException, SQLException {
        return new Liquibase(liquibaseChangelogResolver.getChangelog(), new ClassLoaderResourceAccessor(), getDatabase(tenantIdentifier));
    }

    /**
     * @param tenantIdentifier
     * @return
     * @throws SQLException
     * @throws DatabaseException
     */
    private Database getDatabase(Long tenantIdentifier) throws SQLException, DatabaseException {
        Connection connection = getConnection(tenantIdentifier);
        Database database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(new JdbcConnection(connection));

        DataSourceProperties tenant = rootDataSourceProperties.getTenant();
        DatabaseType databaseType = databaseCreatorStrategyManager.getStrategyType(tenant.getUrl())
                .orElseThrow(() -> new RuntimeException("DatabaseType not found"));

        if (databaseType.isHasSchema()) {
            database.setDefaultSchemaName(databaseType.getDefaultSchemaName());
        }

        return database;
    }

    /**
     * @param tenantIdentifier
     * @return
     * @throws SQLException
     */
    private Connection getConnection(Long tenantIdentifier) throws SQLException {
        TenantDataSource tenantDataSource = dataSourceResolver.getOrCreate(tenantIdentifier);
        DataSource dataSource = tenantDataSource.getDataSource();
        return dataSource.getConnection();
    }
}

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
import org.khasanof.core.tenancy.core.model.SDataSource;
import org.khasanof.core.tenancy.multi.resolver.datasource.DataSourceResolver;

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

    public static final String DEFAULT_SCHEMA_NAME = "public";

    private final String changeLogPath;
    private final DataSourceResolver dataSourceResolver;

    public LiquibaseServiceImpl(String changeLogPath, DataSourceResolver dataSourceResolver) {
        this.dataSourceResolver = dataSourceResolver;
        this.changeLogPath = changeLogPath;
    }

    /**
     *
     */
    @Override
    public void allTenantsMigrations() {
        Map<Long, SDataSource> allDataSources = dataSourceResolver.getAllDataSources();
        allDataSources.keySet().forEach(this::singleTenantMigration);
    }

    /**
     * @param tenantIdentifier
     */
    @Override
    public void singleTenantMigration(Long tenantIdentifier) {
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
        return new Liquibase(changeLogPath, new ClassLoaderResourceAccessor(), getDatabase(tenantIdentifier));
    }

    /**
     * @param tenantIdentifier
     * @return
     * @throws SQLException
     * @throws DatabaseException
     */
    private Database getDatabase(Long tenantIdentifier) throws SQLException, DatabaseException {
        Connection connection = getConnection(tenantIdentifier);
        connection.prepareStatement(String.format("SET search_path TO '%s'", DEFAULT_SCHEMA_NAME)).execute();

        Database database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(new JdbcConnection(connection));
        database.setDefaultSchemaName(DEFAULT_SCHEMA_NAME);

        return database;
    }

    /**
     * @param tenantIdentifier
     * @return
     * @throws SQLException
     */
    private Connection getConnection(Long tenantIdentifier) throws SQLException {
        SDataSource sDataSource = dataSourceResolver.getOrCreate(tenantIdentifier);
        DataSource dataSource = sDataSource.getDataSource();
        return dataSource.getConnection();
    }
}

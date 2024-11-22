package org.khasanof.core.migration.core.liquibase;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.core.env.Environment;
import tech.jhipster.config.liquibase.AsyncSpringLiquibase;
import org.khasanof.core.migration.core.database.DatabaseCreatorService;

import javax.sql.DataSource;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

/**
 * @author Nurislom
 * @see org.khasanof.core.migration.core.liquibase
 * @since 11/11/2024 1:35 PM
 */
public final class AsyncSpringLiquibaseV2Util {

    /**
     *
     * @param env
     * @param executor
     * @param liquibaseDatasource
     * @param liquibaseProperties
     * @param dataSource
     * @param dataSourceProperties
     * @param databaseCreatorService
     * @return
     */
    public static AsyncSpringLiquibase createAsyncSpringLiquibase(Environment env, Executor executor, DataSource liquibaseDatasource,
                                                                  LiquibaseProperties liquibaseProperties, DataSource dataSource,
                                                                  DataSourceProperties dataSourceProperties, DatabaseCreatorService databaseCreatorService) {

        AsyncSpringLiquibase liquibase = new AsyncSpringLiquibaseV2(executor, env, dataSourceProperties, databaseCreatorService);
        DataSource liquibaseDataSource = getDataSource(liquibaseDatasource, liquibaseProperties, dataSource);

        if (liquibaseDataSource != null) {
            liquibase.setCloseDataSourceOnceMigrated(false);
            liquibase.setDataSource(liquibaseDataSource);
        } else {
            liquibase.setDataSource(createNewDataSource(liquibaseProperties, dataSourceProperties));
        }

        return liquibase;
    }

    /**
     *
     * @param liquibaseDataSource
     * @param liquibaseProperties
     * @param dataSource
     * @return
     */
    private static DataSource getDataSource(
            DataSource liquibaseDataSource,
            LiquibaseProperties liquibaseProperties,
            DataSource dataSource
    ) {
        if (liquibaseDataSource != null) {
            return liquibaseDataSource;
        }
        if (liquibaseProperties.getUrl() == null && liquibaseProperties.getUser() == null) {
            return dataSource;
        }
        return null;
    }

    /**
     *
     * @param liquibaseProperties
     * @param dataSourceProperties
     * @return
     */
    private static DataSource createNewDataSource(LiquibaseProperties liquibaseProperties, DataSourceProperties dataSourceProperties) {
        String url = getProperty(liquibaseProperties::getUrl, dataSourceProperties::determineUrl);
        String user = getProperty(liquibaseProperties::getUser, dataSourceProperties::determineUsername);
        String password = getProperty(liquibaseProperties::getPassword, dataSourceProperties::determinePassword);
        return DataSourceBuilder.create().url(url).username(user).password(password).build();
    }

    /**
     *
     * @param property
     * @param defaultValue
     * @return
     */
    private static String getProperty(Supplier<String> property, Supplier<String> defaultValue) {
        return Optional.of(property).map(Supplier::get).orElseGet(defaultValue);
    }
}

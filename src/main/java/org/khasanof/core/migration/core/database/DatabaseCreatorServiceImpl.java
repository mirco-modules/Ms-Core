package org.khasanof.core.migration.core.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.khasanof.core.domain.enumeration.DatabaseType;
import org.khasanof.core.migration.core.database.strategy.DatabaseCreatorStrategy;
import org.khasanof.core.migration.core.database.strategy.manager.DatabaseCreatorStrategyManager;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.Optional;

import static org.khasanof.core.migration.core.database.helper.DatabaseUrlHelper.replaceDatabaseName;

/**
 * @author Nurislom
 * @see org.khasanof.core.migration.core.database
 * @since 11/11/2024 11:16 AM
 */
@Slf4j
@Service
public class DatabaseCreatorServiceImpl implements DatabaseCreatorService {

    private final DatabaseCreatorStrategyManager databaseCreatorStrategyManager;

    public DatabaseCreatorServiceImpl(DatabaseCreatorStrategyManager databaseCreatorStrategyManager) {
        this.databaseCreatorStrategyManager = databaseCreatorStrategyManager;
    }

    /**
     *
     * @param dataSourceProperties
     */
    @Override
    public void createIfNotExist(DataSourceProperties dataSourceProperties) {
        Optional<DatabaseCreatorStrategy> optionalStrategy = databaseCreatorStrategyManager.getStrategy(dataSourceProperties.getUrl());

        DatabaseCreatorStrategy databaseCreatorStrategy = optionalStrategy.orElseThrow(() -> new IllegalArgumentException("DatabaseCreatorStrategy not found!"));
        if (databaseCreatorStrategy.ignore()) {
            log.debug("Ignored create database strategy: {}", databaseCreatorStrategy.getType());
            return;
        }

        String extractDatabaseName = databaseCreatorStrategy.extractDatabaseName(dataSourceProperties.getUrl());
        if (extractDatabaseName == null) {
            throw new IllegalArgumentException("Database name is empty");
        }

        DatabaseType strategyType = databaseCreatorStrategy.getType();
        String replacedJdbcUrl = replaceDatabaseName(dataSourceProperties.getUrl(), strategyType.getDefaultDatabaseName());

        DataSourceBuilder<?> dataSourceBuilder = dataSourceProperties.initializeDataSourceBuilder();
        dataSourceBuilder.url(replacedJdbcUrl);

        DataSource dataSource = dataSourceBuilder.build();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        if (!databaseCreatorStrategy.ignoreCheckExistDatabaseQuery() && checkExistDatabase(extractDatabaseName, jdbcTemplate, databaseCreatorStrategy)) {
            log.debug("Database already created: {}", extractDatabaseName);
            return;
        }
        createDatabase(extractDatabaseName, jdbcTemplate, databaseCreatorStrategy);
    }

    /**
     *
     * @param databaseName
     * @param jdbcTemplate
     * @return
     */
    private Boolean checkExistDatabase(String databaseName, JdbcTemplate jdbcTemplate, DatabaseCreatorStrategy databaseCreatorStrategy) {
        log.debug("Started checking database: {}", databaseName);
        return jdbcTemplate.query(databaseCreatorStrategy.checkExistDatabaseQuery(databaseName), ResultSet::next);
    }

    /**
     *
     * @param databaseName
     * @param jdbcTemplate
     */
    private void createDatabase(String databaseName, JdbcTemplate jdbcTemplate, DatabaseCreatorStrategy databaseCreatorStrategy) {
        log.debug("Started creating database: {}", databaseName);
        jdbcTemplate.execute(databaseCreatorStrategy.createDatabaseQuery(databaseName));
    }
}

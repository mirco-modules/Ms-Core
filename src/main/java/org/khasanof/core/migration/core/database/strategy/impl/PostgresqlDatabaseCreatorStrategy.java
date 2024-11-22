package org.khasanof.core.migration.core.database.strategy.impl;

import org.springframework.stereotype.Component;
import org.khasanof.core.domain.enumeration.DatabaseType;
import org.khasanof.core.migration.core.database.strategy.AbstractDatabaseCreatorStrategy;

/**
 * @author Nurislom
 * @see org.khasanof.core.migration.core.database.strategy.impl
 * @since 11/11/2024 12:47 PM
 */
@Component
public class PostgresqlDatabaseCreatorStrategy extends AbstractDatabaseCreatorStrategy {

    /**
     *
     * @param databaseName
     * @return
     */
    @Override
    public String createDatabaseQuery(String databaseName) {
        return "CREATE DATABASE " + databaseName;
    }

    /**
     *
     * @param databaseName
     * @return
     */
    @Override
    public String checkExistDatabaseQuery(String databaseName) {
        return "SELECT 1 FROM pg_database WHERE datname = '" + databaseName + "'";
    }

    /**
     *
     * @return
     */
    @Override
    public DatabaseType getType() {
        return DatabaseType.POSTGRESQL;
    }
}

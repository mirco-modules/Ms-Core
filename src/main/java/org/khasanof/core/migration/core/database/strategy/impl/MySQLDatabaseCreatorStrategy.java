package org.khasanof.core.migration.core.database.strategy.impl;

import org.springframework.stereotype.Component;
import org.khasanof.core.domain.enumeration.DatabaseType;
import org.khasanof.core.migration.core.database.strategy.AbstractDatabaseCreatorStrategy;

/**
 * @author Nurislom
 * @see org.khasanof.core.migration.core.database.strategy.impl
 * @since 11/11/2024 12:55 PM
 */
@Component
public class MySQLDatabaseCreatorStrategy extends AbstractDatabaseCreatorStrategy {

    /**
     *
     * @param databaseName
     * @return
     */
    @Override
    public String createDatabaseQuery(String databaseName) {
        return "CREATE DATABASE IF NOT EXISTS " + databaseName;
    }

    /**
     *
     * @return
     */
    @Override
    public Boolean ignoreCheckExistDatabaseQuery() {
        return Boolean.TRUE;
    }

    /**
     *
     * @return
     */
    @Override
    public DatabaseType getType() {
        return DatabaseType.MYSQL;
    }
}

package org.khasanof.core.migration.core.database.strategy;

import org.khasanof.core.domain.enumeration.DatabaseType;

/**
 * @author Nurislom
 * @see org.khasanof.core.migration.core.database.strategy
 * @since 11/11/2024 11:50 AM
 */
public interface DatabaseCreatorStrategy {

    /**
     *
     * @param jdbcUrl
     * @return
     */
    boolean isSupport(String jdbcUrl);

    /**
     *
     * @param jdbcUrl
     * @return
     */
    String extractDatabaseName(String jdbcUrl);

    /**
     *
     * @param databaseName
     * @return
     */
    String createDatabaseQuery(String databaseName);

    /**
     *
     * @param databaseName
     * @return
     */
    String checkExistDatabaseQuery(String databaseName);

    /**
     *
     * @return
     */
    Boolean ignoreCheckExistDatabaseQuery();

    /**
     *
     * @return
     */
    DatabaseType getType();

    /**
     *
     * @return
     */
    Boolean ignore();
}

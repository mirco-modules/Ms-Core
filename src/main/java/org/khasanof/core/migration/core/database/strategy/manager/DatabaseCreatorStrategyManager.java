package org.khasanof.core.migration.core.database.strategy.manager;

import org.khasanof.core.migration.core.database.strategy.DatabaseCreatorStrategy;

import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.core.migration.core.database.strategy.manager
 * @since 11/11/2024 12:23 PM
 */
public interface DatabaseCreatorStrategyManager {

    /**
     *
     * @param jdbcUrl
     * @return
     */
    Optional<DatabaseCreatorStrategy> getStrategy(String jdbcUrl);
}

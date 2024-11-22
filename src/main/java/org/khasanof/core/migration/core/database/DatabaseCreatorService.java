package org.khasanof.core.migration.core.database;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

/**
 * @author Nurislom
 * @see org.khasanof.core.migration.core.database
 * @since 11/11/2024 11:16 AM
 */
public interface DatabaseCreatorService {

    /**
     *
     * @param dataSourceProperties
     */
    void createIfNotExist(DataSourceProperties dataSourceProperties);
}

package org.khasanof.core.tenancy.multi.database;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.multi.database
 * @since 11/2/2024 3:55 PM
 */
public interface CreateDatabaseService {

    /**
     *
     * @param databaseName
     */
    void create(String databaseName);
}

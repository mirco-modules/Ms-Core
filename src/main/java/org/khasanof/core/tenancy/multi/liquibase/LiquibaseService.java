package org.khasanof.core.tenancy.multi.liquibase;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.multi.liquibase
 * @since 11/2/2024 5:58 PM
 */
public interface LiquibaseService {

    /**
     *
     */
    void allTenantsMigrations();

    /**
     *
     * @param tenantIdentifier
     */
    void migrate(Long tenantIdentifier);
}

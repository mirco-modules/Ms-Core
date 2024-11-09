package org.khasanof.core.tenancy.multi.liquibase.resolver;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.multi.liquibase.resolver
 * @since 11/9/2024 2:22 PM
 */
public interface LiquibaseChangelogResolver {

    /**
     *
     * @return
     */
    String getChangelog();
}

package org.khasanof.core.tenancy.multi.liquibase.resolver;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.multi.liquibase.resolver
 * @since 11/9/2024 2:23 PM
 */
public class LiquibaseChangelogResolverImpl implements LiquibaseChangelogResolver {

    private static final String CHANGE_LOG_PATH = "config/liquibase/master.xml";

    /**
     *
     * @return
     */
    @Override
    public String getChangelog() {
        return CHANGE_LOG_PATH;
    }
}

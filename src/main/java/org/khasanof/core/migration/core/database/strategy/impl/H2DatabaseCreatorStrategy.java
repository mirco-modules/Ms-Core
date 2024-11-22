package org.khasanof.core.migration.core.database.strategy.impl;

import org.springframework.stereotype.Component;
import org.khasanof.core.domain.enumeration.DatabaseType;
import org.khasanof.core.migration.core.database.strategy.AbstractDatabaseCreatorStrategy;

import java.util.regex.Matcher;

/**
 * @author Nurislom
 * @see org.khasanof.core.migration.core.database.strategy.impl
 * @since 11/11/2024 12:50 PM
 */
@Component
public class H2DatabaseCreatorStrategy extends AbstractDatabaseCreatorStrategy {

    /**
     *
     * @param databaseName
     * @return
     */
    @Override
    public String createDatabaseQuery(String databaseName) {
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public DatabaseType getType() {
        return DatabaseType.H2;
    }

    /**
     *
     * @param matcher
     * @return
     */
    @Override
    protected String selectRegexGroup(Matcher matcher) {
        return matcher.group(2);
    }

    /**
     *
     * @return
     */
    @Override
    public Boolean ignore() {
        return Boolean.TRUE;
    }
}

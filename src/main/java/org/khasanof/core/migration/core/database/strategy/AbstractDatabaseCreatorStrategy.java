package org.khasanof.core.migration.core.database.strategy;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Nurislom
 * @see org.khasanof.core.migration.core.database.strategy
 * @since 11/11/2024 11:55 AM
 */
public abstract class AbstractDatabaseCreatorStrategy implements DatabaseCreatorStrategy {

    private final String pattern = "^jdbc:(\\w+):.*";
    private final Pattern regex = Pattern.compile(pattern);

    /**
     *
     * @param jdbcUrl
     * @return
     */
    @Override
    public boolean isSupport(String jdbcUrl) {
        checkJdbcUrl(jdbcUrl);
        Matcher matcher = regex.matcher(jdbcUrl);

        if (!matcher.find()) {
            return false;
        }
        String databaseName = getType().getDatabaseName();
        return Objects.equals(matcher.group(1), databaseName);
    }

    /**
     *
     * @param jdbcUrl
     * @return
     */
    @Override
    public String extractDatabaseName(String jdbcUrl) {
        checkJdbcUrl(jdbcUrl);
        String extractDbRegex = getType().getExtractDatabaseNameRegex();
        Pattern extractDbNamePattern = Pattern.compile(extractDbRegex);

        Matcher matcher = extractDbNamePattern.matcher(jdbcUrl);
        if (!matcher.find()) {
            return null;
        }
        return selectRegexGroup(matcher);
    }

    /**
     *
     * @param databaseName
     * @return
     */
    @Override
    public String checkExistDatabaseQuery(String databaseName) {
        return null;
    }

    /**
     *
     * @param matcher
     * @return
     */
    protected String selectRegexGroup(Matcher matcher) {
        return matcher.group(1);
    }

    /**
     *
     * @param jdbcUrl
     */
    private void checkJdbcUrl(String jdbcUrl) {
        if (jdbcUrl == null || jdbcUrl.isEmpty()) {
            throw new IllegalArgumentException("JDBC URL cannot be null or empty");
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Boolean ignoreCheckExistDatabaseQuery() {
        return Boolean.FALSE;
    }

    /**
     *
     * @return
     */
    @Override
    public Boolean ignore() {
        return Boolean.FALSE;
    }
}

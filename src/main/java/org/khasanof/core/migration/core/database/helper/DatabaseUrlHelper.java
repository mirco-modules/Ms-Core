package org.khasanof.core.migration.core.database.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Nurislom
 * @see org.khasanof.core.migration.core.database.helper
 * @since 11/11/2024 12:34 PM
 */
public final class DatabaseUrlHelper {

    /**
     *
     * @param jdbcUrl
     * @param newDbName
     * @return
     */
    public static String replaceDatabaseName(String jdbcUrl, String newDbName) {
        if (jdbcUrl == null || jdbcUrl.isEmpty()) {
            throw new IllegalArgumentException("JDBC URL cannot be null or empty");
        }

        if (newDbName == null || newDbName.isEmpty()) {
            throw new IllegalArgumentException("New database name cannot be null or empty");
        }

        // Regular expression to match the database name part of the JDBC URL
        String pattern = "(jdbc:[^:]+://[^/]+/)([^/?]+)(.*)";

        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(jdbcUrl);

        if (matcher.find()) {
            // Replace the database name (group 2) with the new database name
            return matcher.group(1) + newDbName + matcher.group(3);
        } else {
            throw new IllegalArgumentException("Unable to find database name in the JDBC URL");
        }
    }
}

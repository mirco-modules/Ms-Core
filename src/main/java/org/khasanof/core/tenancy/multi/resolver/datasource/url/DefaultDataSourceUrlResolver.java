package org.khasanof.core.tenancy.multi.resolver.datasource.url;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.khasanof.core.tenancy.multi.helper.DatabaseNameHelper;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.multi.resolver.datasource.url
 * @since 1/23/2025 5:42 PM
 */
public class DefaultDataSourceUrlResolver implements DataSourceUrlResolver {

    public static final String SLASH = "/";

    private final DatabaseNameHelper databaseNameHelper;
    private final DataSourceProperties dataSourceProperties;

    public DefaultDataSourceUrlResolver(DatabaseNameHelper databaseNameHelper, DataSourceProperties dataSourceProperties) {
        this.databaseNameHelper = databaseNameHelper;
        this.dataSourceProperties = dataSourceProperties;
    }

    /**
     *
     * @param tenantIdentifier
     * @return
     */
    @Override
    public String resolve(Long tenantIdentifier) {
        return resolve(tenantIdentifier, dataSourceProperties.getUrl());
    }

    /**
     *
     * @param tenantIdentifier
     * @param jdbcUrl
     * @return
     */
    @Override
    public String resolve(Long tenantIdentifier, String jdbcUrl) {
        String url = removeLastPathSegment(jdbcUrl);
        String databaseName = databaseNameHelper.getDatabaseName(tenantIdentifier);
        return url.concat(SLASH).concat(databaseName);
    }

    /**
     *
     * @param url
     * @return
     */
    public String removeLastPathSegment(String url) {
        if (url == null || !url.contains(SLASH)) {
            return url;
        }

        int lastSlashIndex = url.lastIndexOf(SLASH);
        return url.substring(0, lastSlashIndex);
    }
}

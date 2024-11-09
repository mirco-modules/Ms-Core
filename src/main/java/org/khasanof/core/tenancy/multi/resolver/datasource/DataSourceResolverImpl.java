package org.khasanof.core.tenancy.multi.resolver.datasource;

import org.khasanof.core.tenancy.core.model.TenantDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.khasanof.core.tenancy.multi.helper.DatabaseNameHelper;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.multi.resolver.datasource
 * @since 11/2/2024 3:42 PM
 */
public class DataSourceResolverImpl implements DataSourceResolver {

    public static final String SLASH = "/";
    private final Map<Long, TenantDataSource> dataSources = new ConcurrentHashMap<>();
    private final Map<Long, DataSource> resolvedDataSources = new ConcurrentHashMap<>();

    private final DatabaseNameHelper databaseNameHelper;
    private final DataSourceProperties dataSourceProperties;

    public DataSourceResolverImpl(DatabaseNameHelper databaseNameHelper,
                                  DataSourceProperties dataSourceProperties) {

        this.databaseNameHelper = databaseNameHelper;
        this.dataSourceProperties = dataSourceProperties;
    }

    /**
     *
     * @param tenantIdentifier
     * @return
     */
    @Override
    public TenantDataSource getOrCreate(Long tenantIdentifier) {
        if (!dataSources.containsKey(tenantIdentifier)) {
            dataSources.put(tenantIdentifier, create(tenantIdentifier));
        }
        return dataSources.get(tenantIdentifier);
    }

    /**
     *
     * @param tenantIdentifier
     * @return
     */
    private TenantDataSource create(Long tenantIdentifier) {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        String targetUrl = getTargetUrl(tenantIdentifier);

        dataSourceBuilder.url(targetUrl);
        dataSourceBuilder.type(dataSourceProperties.getType());
        dataSourceBuilder.password(dataSourceProperties.getPassword());
        dataSourceBuilder.username(dataSourceProperties.getUsername());
        dataSourceBuilder.driverClassName(dataSourceProperties.getDriverClassName());

        DataSource dataSource = dataSourceBuilder.build();

        TenantDataSource tenantDataSource = new TenantDataSource(false, dataSource);
        dataSources.put(tenantIdentifier, tenantDataSource);
        resolvedDataSources.put(tenantIdentifier, dataSource);
        return tenantDataSource;
    }

    /**
     *
     * @param tenantIdentifier
     * @return
     */
    private String getTargetUrl(Long tenantIdentifier) {
        String url = removeLastPathSegment(dataSourceProperties.getUrl());
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

    /**
     *
     * @param tenantIdentifier
     * @return
     */
    @Override
    public boolean existDataSource(Long tenantIdentifier) {
        return dataSources.containsKey(tenantIdentifier);
    }

    /**
     *
     * @return
     */
    @Override
    public Map<Long, TenantDataSource> getDataSources() {
        return this.dataSources;
    }

    /**
     *
     * @return
     */
    @Override
    public Map<Long, DataSource> getResolvedDataSources() {
        return this.resolvedDataSources;
    }
}

package org.khasanof.core.tenancy.multi.resolver.datasource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.khasanof.core.tenancy.core.model.SDataSource;
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

    private final Map<Long, SDataSource> dataSources = new ConcurrentHashMap<>();

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
    public SDataSource getOrCreate(Long tenantIdentifier) {
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
    @Override
    public boolean existDataSource(Long tenantIdentifier) {
        return dataSources.containsKey(tenantIdentifier);
    }

    /**
     *
     * @return
     */
    @Override
    public Map<Long, SDataSource> getAllDataSources() {
        return this.dataSources;
    }

    /**
     *
     * @param tenantIdentifier
     * @return
     */
    private SDataSource create(Long tenantIdentifier) {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();

        String url = removeLastPathSegment(dataSourceProperties.getUrl());
        String databaseName = databaseNameHelper.getDatabaseName(tenantIdentifier);
        dataSourceBuilder.url(url + "/" + databaseName);

        dataSourceBuilder.type(dataSourceProperties.getType());
        dataSourceBuilder.password(dataSourceProperties.getPassword());
        dataSourceBuilder.username(dataSourceProperties.getUsername());
        dataSourceBuilder.driverClassName(dataSourceProperties.getDriverClassName());

        DataSource dataSource = dataSourceBuilder.build();

        SDataSource sDataSource = new SDataSource(false, dataSource);
        dataSources.put(tenantIdentifier, sDataSource);
        return sDataSource;
    }

    /**
     *
     * @param url
     * @return
     */
    public String removeLastPathSegment(String url) {
        if (url == null || !url.contains("/")) {
            return url;
        }

        int lastSlashIndex = url.lastIndexOf("/");
        return url.substring(0, lastSlashIndex);
    }
}

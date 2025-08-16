package org.khasanof.core.tenancy.multi.resolver.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.khasanof.core.config.RootDataSourceProperties;
import org.khasanof.core.service.hikari.HikariDataSourceConfigurerService;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.khasanof.core.tenancy.core.model.TenantDataSource;
import org.khasanof.core.tenancy.multi.resolver.datasource.url.DataSourceUrlResolver;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.multi.resolver.datasource
 * @since 11/2/2024 3:42 PM
 */
@Slf4j
public class DataSourceResolverImpl implements DataSourceResolver {

    private final Map<Long, TenantDataSource> dataSources = new ConcurrentHashMap<>();
    private final Map<Long, DataSource> resolvedDataSources = new ConcurrentHashMap<>();

    private final DataSourceProperties dataSourceProperties;
    private final DataSourceUrlResolver dataSourceUrlResolver;
    private final RootDataSourceProperties rootDataSourceProperties;
    private final HikariDataSourceConfigurerService hikariDataSourceConfigurerService;

    public DataSourceResolverImpl(DataSourceProperties dataSourceProperties, DataSourceUrlResolver dataSourceUrlResolver, RootDataSourceProperties rootDataSourceProperties, HikariDataSourceConfigurerService hikariDataSourceConfigurerService) {
        this.dataSourceProperties = dataSourceProperties;
        this.dataSourceUrlResolver = dataSourceUrlResolver;
        this.rootDataSourceProperties = rootDataSourceProperties;
        this.hikariDataSourceConfigurerService = hikariDataSourceConfigurerService;
    }

    /**
     *
     * @param tenantIdentifier
     * @return
     */
    @Override
    public TenantDataSource getOrCreate(Long tenantIdentifier) {
        log.debug("Request method get or create tenant datasource: {}", tenantIdentifier);
        if (!dataSources.containsKey(tenantIdentifier)) {
            log.debug("Datasource not found by {}", tenantIdentifier);
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
        String targetUrl = dataSourceUrlResolver.resolve(tenantIdentifier, dataSourceProperties.getUrl());

        dataSourceBuilder.url(targetUrl);
        dataSourceBuilder.type(dataSourceProperties.getType());
        dataSourceBuilder.password(dataSourceProperties.getPassword());
        dataSourceBuilder.username(dataSourceProperties.getUsername());
        dataSourceBuilder.driverClassName(dataSourceProperties.getDriverClassName());

        DataSource dataSource = dataSourceBuilder.build();
        if (dataSource instanceof HikariDataSource hikariDataSource) {
            hikariDataSourceConfigurerService.configurer(hikariDataSource, rootDataSourceProperties.getTenantProperties());
        }

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

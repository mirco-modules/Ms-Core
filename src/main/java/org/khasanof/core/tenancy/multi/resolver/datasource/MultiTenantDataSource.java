package org.khasanof.core.tenancy.multi.resolver.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.khasanof.core.tenancy.multi.manager.DataSourceManager;
import org.khasanof.core.tenancy.multi.resolver.TenantIdentifierResolver;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.multi.resolver.datasource
 * @since 11/2/2024 4:06 PM
 */
public class MultiTenantDataSource extends AbstractRoutingDataSource {

    private final DataSourceManager dataSourceManager;
    private final TenantIdentifierResolver tenantIdentifierResolver;

    public MultiTenantDataSource(DataSourceManager dataSourceManager, TenantIdentifierResolver tenantIdentifierResolver) {
        this.dataSourceManager = dataSourceManager;
        this.tenantIdentifierResolver = tenantIdentifierResolver;
    }

    /**
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return tenantIdentifierResolver.resolveCurrentTenantIdentifier();
    }

    /**
     * @return
     * @throws SQLException
     */
    @Override
    public Connection getConnection() throws SQLException {
        var dataSource = getCurrentTenantDataSource();
        return dataSource.getConnection();
    }

    /**
     *
     * @return
     */
    private DataSource getCurrentTenantDataSource() {
        return dataSourceManager.getOrCreate(tenantIdentifierResolver.resolveCurrentTenantIdentifier());
    }
}

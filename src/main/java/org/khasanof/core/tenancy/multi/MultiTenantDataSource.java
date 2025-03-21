package org.khasanof.core.tenancy.multi;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.khasanof.core.domain.enumeration.RepositoryType;
import org.khasanof.core.tenancy.core.TenantContext;
import org.khasanof.core.tenancy.multi.manager.DataSourceManager;
import org.khasanof.core.tenancy.multi.resolver.TenantIdentifierResolver;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

import static org.khasanof.core.tenancy.core.TenantContext.getCurrentTenantRepositoryType;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.multi.resolver.datasource
 * @since 11/2/2024 4:06 PM
 */
public class MultiTenantDataSource extends AbstractRoutingDataSource {

    private final DataSource defaultDataSource;
    private final DataSourceManager dataSourceManager;
    private final TenantIdentifierResolver tenantIdentifierResolver;

    public MultiTenantDataSource(DataSource defaultDataSource, DataSourceManager dataSourceManager, TenantIdentifierResolver tenantIdentifierResolver) {
        this.defaultDataSource = defaultDataSource;
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
        if (checkDefaultDatasourceCondition()) {
            return defaultDataSource.getConnection();
        }
        return getMultitenanyConnection();
    }

    /**
     *
     * @return
     */
    private boolean checkDefaultDatasourceCondition() {
        return Objects.equals(getCurrentTenantRepositoryType(), RepositoryType.DEFAULT) && Objects.equals(TenantContext.getCurrentTenantId(), 0L);
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    private Connection getMultitenanyConnection() throws SQLException {
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

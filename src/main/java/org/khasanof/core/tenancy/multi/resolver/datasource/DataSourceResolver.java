package org.khasanof.core.tenancy.multi.resolver.datasource;

import org.khasanof.core.tenancy.core.model.TenantDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.multi.resolver.datasource
 * @since 11/2/2024 3:40 PM
 */
public interface DataSourceResolver {

    /**
     *
     * @param tenantIdentifier
     * @return
     */
    TenantDataSource getOrCreate(Long tenantIdentifier);

    /**
     *
     * @param tenantIdentifier
     * @return
     */
    boolean existDataSource(Long tenantIdentifier);

    /**
     *
     * @return
     */
    Map<Long, TenantDataSource> getDataSources();

    /**
     *
     * @return
     */
    Map<Long, DataSource> getResolvedDataSources();
}

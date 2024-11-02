package org.khasanof.core.tenancy.multi.resolver.datasource;

import org.khasanof.core.tenancy.core.model.SDataSource;

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
    SDataSource getOrCreate(Long tenantIdentifier);

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
    Map<Long, SDataSource> getAllDataSources();
}

package org.khasanof.core.tenancy.multi.manager;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.multi.manager
 * @since 11/2/2024 6:29 PM
 */
public interface DataSourceManager {

    /**
     *
     * @param tenantIdentifier
     * @return
     */
    DataSource getOrCreate(Long tenantIdentifier);

    /**
     *
     * @param tenantIdentifier
     * @return
     */
    DataSource get(Long tenantIdentifier);

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
    Map<Long, DataSource> getAllDataSources();
}

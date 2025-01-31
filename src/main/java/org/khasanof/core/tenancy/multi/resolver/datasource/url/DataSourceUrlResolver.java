package org.khasanof.core.tenancy.multi.resolver.datasource.url;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.multi.resolver.datasource.url
 * @since 1/23/2025 5:41 PM
 */
public interface DataSourceUrlResolver {

    /**
     *
     * @param tenantIdentifier
     * @return
     */
    String resolve(Long tenantIdentifier);

    /**
     *
     * @param tenantIdentifier
     * @param jdbcUrl
     * @return
     */
    String resolve(Long tenantIdentifier, String jdbcUrl);
}

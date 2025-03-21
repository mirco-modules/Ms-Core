package org.khasanof.core.tenancy;

import org.khasanof.core.domain.enumeration.TenancyResolverType;
import org.khasanof.core.domain.enumeration.TenancyType;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy
 * @since 11/2/2024 1:08 PM
 */
public interface TenancyConfigurer {

    /**
     *
     * @return
     */
    TenancyType getType();

    /**
     *
     * @return
     */
    TenancyResolverType getResolverType();
}

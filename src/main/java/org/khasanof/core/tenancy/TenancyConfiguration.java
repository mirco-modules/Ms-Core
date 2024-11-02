package org.khasanof.core.tenancy;

import org.khasanof.core.enumeration.TenancyResolverType;
import org.khasanof.core.enumeration.TenancyType;

/**
 * @author Nurislom
 * @see org.khasanof.core
 * @since 11/2/2024 1:08 PM
 */
public interface TenancyConfiguration {

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

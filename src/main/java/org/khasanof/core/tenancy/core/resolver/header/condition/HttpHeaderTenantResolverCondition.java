package org.khasanof.core.tenancy.core.resolver.header.condition;

import org.khasanof.core.domain.enumeration.TenancyResolverType;
import org.khasanof.core.tenancy.core.resolver.condition.AbstractTenantResolverCondition;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.core.resolver.header
 * @since 11/9/2024 4:51 PM
 */
public class HttpHeaderTenantResolverCondition extends AbstractTenantResolverCondition {

    /**
     *
     * @return
     */
    @Override
    public TenancyResolverType tenancyResolverType() {
        return TenancyResolverType.HTTP_HEADER;
    }
}

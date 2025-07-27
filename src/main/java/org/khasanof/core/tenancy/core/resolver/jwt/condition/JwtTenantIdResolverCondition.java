package org.khasanof.core.tenancy.core.resolver.jwt.condition;

import org.khasanof.core.domain.enumeration.TenancyResolverType;
import org.khasanof.core.tenancy.core.resolver.condition.AbstractTenantResolverCondition;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.core.resolver.jwt.condition
 * @since 7/13/2025 4:02 PM
 */
public class JwtTenantIdResolverCondition extends AbstractTenantResolverCondition {

    /**
     *
     * @return
     */
    @Override
    public TenancyResolverType tenancyResolverType() {
        return TenancyResolverType.SECURITY;
    }
}

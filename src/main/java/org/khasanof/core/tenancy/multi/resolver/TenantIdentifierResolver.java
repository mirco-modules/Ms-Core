package org.khasanof.core.tenancy.multi.resolver;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.khasanof.core.tenancy.core.TenantContext;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.core
 * @since 11/2/2024 1:19 PM
 */
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver<Long> {

    /**
     *
     * @return
     */
    @Override
    public Long resolveCurrentTenantIdentifier() {
        Long tenant = TenantContext.getCurrentTenant();
        return Objects.requireNonNullElse(tenant, 0L);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean validateExistingCurrentSessions() {
        return false;
    }
}

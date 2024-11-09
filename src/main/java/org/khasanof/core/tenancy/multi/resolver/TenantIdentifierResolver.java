package org.khasanof.core.tenancy.multi.resolver;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.khasanof.core.tenancy.core.TenantContext;

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
        return TenantContext.getCurrentTenantId();
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

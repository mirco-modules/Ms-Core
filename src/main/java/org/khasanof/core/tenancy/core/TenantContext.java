package org.khasanof.core.tenancy.core;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.core
 * @since 11/2/2024 1:12 PM
 */
public abstract class TenantContext {

    private static final ThreadLocal<Long> tenantIdContext = new ThreadLocal<>();

    /**
     *
     * @param tenantId
     */
    public static void setCurrentTenant(Long tenantId) {
        tenantIdContext.set(tenantId);
    }

    /**
     *
     * @return
     */
    public static Long getCurrentTenant() {
        return Objects.requireNonNullElse(tenantIdContext.get(), 0L);
    }
}

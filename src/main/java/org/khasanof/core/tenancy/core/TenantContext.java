package org.khasanof.core.tenancy.core;

import org.khasanof.core.enumeration.RepositoryType;
import org.khasanof.core.tenancy.core.model.Tenant;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.core
 * @since 11/8/2024 3:39 PM
 */
public abstract class TenantContext {

    public static Tenant DEFAULT_TENANT = new Tenant(0L, RepositoryType.DEFAULT);

    private static final ThreadLocal<Tenant> tenantTypeContext = new ThreadLocal<>();

    /**
     *
     * @param tenant
     */
    public static void setTenant(Tenant tenant) {
        tenantTypeContext.set(tenant);
    }

    /**
     *
     * @return
     */
    public static Tenant getCurrentTenant() {
        return Objects.requireNonNullElse(tenantTypeContext.get(), DEFAULT_TENANT);
    }

    /**
     *
     * @return
     */
    public static RepositoryType getCurrentTenantRepositoryType() {
        return getCurrentTenant().getRepositoryType();
    }

    /**
     *
     * @return
     */
    public static Long getCurrentTenantId() {
        return getCurrentTenant().getTenantId();
    }
}

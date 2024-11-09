package org.khasanof.core.tenancy.core.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.khasanof.core.enumeration.RepositoryType;
import org.khasanof.core.tenancy.core.TenantContext;
import org.khasanof.core.tenancy.core.model.Tenant;

/**
 * @author Nurislom
 * @see uz.devops.tenancy.core.aop
 * @since 11/8/2024 3:53 PM
 */
@Aspect
public class TenantAspect {

    /**
     *
     */
    @Before("execution(* org.khasanof.core.tenancy.core.repository.MultitenancyRepository+.*(..))")
    public void beforeRepositoryMethods() {
        Tenant currentTenant = TenantContext.getCurrentTenant();
        currentTenant.setRepositoryType(RepositoryType.MULTI_TENANCY);
    }
}

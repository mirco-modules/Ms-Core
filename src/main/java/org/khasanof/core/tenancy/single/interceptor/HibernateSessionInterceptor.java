package org.khasanof.core.tenancy.single.interceptor;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.khasanof.core.tenancy.core.TenantContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Nurislom
 * @see org.khasanof.core
 * @since 11/2/2024 2:43 PM
 */
public class HibernateSessionInterceptor {

    private final EntityManager entityManager;

    public HibernateSessionInterceptor(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void applyTenantFilter() {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("tenantFilter")
                .setParameter("tenantId", TenantContext.getCurrentTenant());
    }
}
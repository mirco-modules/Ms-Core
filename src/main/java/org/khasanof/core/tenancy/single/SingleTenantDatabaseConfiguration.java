package org.khasanof.core.tenancy.single;

import jakarta.persistence.EntityManager;
import org.khasanof.core.tenancy.single.condition.SingleTenantCondition;
import org.khasanof.core.tenancy.single.interceptor.HibernateSessionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.single
 * @since 11/2/2024 3:14 PM
 */
@Configuration
@Conditional({SingleTenantCondition.class})
public class SingleTenantDatabaseConfiguration {

    /**
     *
     * @param entityManager
     * @return
     */
    @Bean
    public HibernateSessionInterceptor hibernateSessionInterceptor(EntityManager entityManager) {
        return new HibernateSessionInterceptor(entityManager);
    }
}

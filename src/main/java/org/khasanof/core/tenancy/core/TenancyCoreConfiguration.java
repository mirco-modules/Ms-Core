package org.khasanof.core.tenancy.core;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.khasanof.core.tenancy.TenancyConfigurer;
import org.khasanof.core.tenancy.core.aop.TenantAspect;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.core
 * @since 11/9/2024 4:41 PM
 */
@Configuration
@ConditionalOnBean({TenancyConfigurer.class})
public class TenancyCoreConfiguration {

    /**
     *
     * @return
     */
    @Bean
    public TenantAspect tenantAspect() {
        return new TenantAspect();
    }
}

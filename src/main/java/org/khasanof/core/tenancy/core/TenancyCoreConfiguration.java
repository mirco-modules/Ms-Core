package org.khasanof.core.tenancy.core;

import org.khasanof.core.tenancy.TenancyConfigurer;
import org.khasanof.core.tenancy.core.aop.TenantAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy
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

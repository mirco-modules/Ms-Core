package org.khasanof.core.tenancy.core.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.khasanof.core.tenancy.single.config.InterceptorRegistryConfiguration;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.core
 * @since 11/2/2024 4:49 PM
 */
@Configuration
public class TenantInterceptorConfiguration {

    /**
     *
     * @return
     */
    @Bean
    public InterceptorRegistryConfiguration interceptorRegistryConfiguration() {
        return new InterceptorRegistryConfiguration();
    }
}

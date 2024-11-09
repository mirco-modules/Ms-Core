package org.khasanof.core.tenancy.core.resolver.header;

import org.khasanof.core.tenancy.core.resolver.header.condition.HttpHeaderTenantResolverCondition;
import org.khasanof.core.tenancy.core.resolver.header.interceptor.InterceptorRegistryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy
 * @since 11/9/2024 4:52 PM
 */
@Configuration
@Conditional({HttpHeaderTenantResolverCondition.class})
public class HttpHeaderTenantResolverConfiguration {

    /**
     *
     * @return
     */
    @Bean
    public InterceptorRegistryConfigurer interceptorRegistryConfiguration() {
        return new InterceptorRegistryConfigurer();
    }
}
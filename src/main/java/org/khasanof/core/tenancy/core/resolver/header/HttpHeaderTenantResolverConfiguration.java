package org.khasanof.core.tenancy.core.resolver.header;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.khasanof.core.tenancy.core.resolver.header.condition.HttpHeaderTenantResolverCondition;
import org.khasanof.core.tenancy.core.resolver.header.interceptor.HttpHeaderInterceptorRegistryConfigurer;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.core.resolver.header
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
    public HttpHeaderInterceptorRegistryConfigurer interceptorRegistryConfiguration() {
        return new HttpHeaderInterceptorRegistryConfigurer();
    }
}

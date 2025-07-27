package org.khasanof.core.tenancy.core.resolver.jwt;

import org.khasanof.core.tenancy.core.resolver.jwt.condition.JwtTenantIdResolverCondition;
import org.khasanof.core.tenancy.core.resolver.jwt.interceptor.JwtInterceptorRegistryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.core.resolver.header
 * @since 7/13/2025 3:57 PM
 */
@Configuration
@Conditional({JwtTenantIdResolverCondition.class})
public class JwtTenantIdResolverConfiguration {

    /**
     *
     * @return
     */
    @Bean
    public JwtInterceptorRegistryConfigurer jwtInterceptorRegistryConfigurer() {
        return new JwtInterceptorRegistryConfigurer();
    }
}

package org.khasanof.core.tenancy.core.resolver.jwt.interceptor;

import org.khasanof.core.tenancy.core.resolver.header.interceptor.HttpHeaderTenantIdResolverInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.core.resolver.jwt.interceptor
 * @since 7/13/2025 4:07 PM
 */
public class JwtInterceptorRegistryConfigurer implements WebMvcConfigurer {

    /**
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtTenantIdResolverInterceptor());
    }
}

package org.khasanof.core.tenancy.core.resolver.header.interceptor;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.core.resolver.header.interceptor
 * @since 11/2/2024 2:21 PM
 */
public class InterceptorRegistryConfigurer implements WebMvcConfigurer {

    /**
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TenantIdentifierInterceptor());
    }
}

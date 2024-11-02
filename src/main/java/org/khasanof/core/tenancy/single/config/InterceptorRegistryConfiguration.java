package org.khasanof.core.tenancy.single.config;

import org.khasanof.core.tenancy.core.interceptor.TenantIdentifierInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Nurislom
 * @see org.khasanof.core
 * @since 11/2/2024 2:21 PM
 */
public class InterceptorRegistryConfiguration implements WebMvcConfigurer {

    /**
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TenantIdentifierInterceptor());
    }
}

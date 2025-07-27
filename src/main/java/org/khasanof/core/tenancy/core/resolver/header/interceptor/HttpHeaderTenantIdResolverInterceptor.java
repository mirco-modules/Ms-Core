package org.khasanof.core.tenancy.core.resolver.header.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.khasanof.core.tenancy.core.TenantContext;
import org.khasanof.core.tenancy.core.model.Tenant;

/**
 * Interceptor to identify and set the current tenant based on the incoming request header.
 *
 * @author Nurislom
 * @see org.khasanof.core.tenancy.single.interceptor
 * @since 11/2/2024 2:17 PM
 */
public class HttpHeaderTenantIdResolverInterceptor implements HandlerInterceptor {

    public static final String TENANT_ID = "X-Tenant-Id";

    /**
     * Intercepts the incoming HTTP request and extracts the tenant ID from the request header.
     * If a tenant ID is found in the {@code X-Tenant-Id} header, it is set in the {@link TenantContext}
     * for further processing in a multi-tenant environment.
     *
     * @param request  the current HTTP request
     * @param response the current HTTP response
     * @param handler  the chosen handler to execute, for type and/or instance evaluation
     * @return {@code true} if the execution chain should proceed with the next interceptor or the handler itself;
     *         {@code false} if the interceptor has already handled the response
     * @throws Exception if an error occurs during request processing
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tenantId = request.getHeader(TENANT_ID);
        if (tenantId != null) {
            TenantContext.setTenant(new Tenant(Long.valueOf(tenantId)));
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}

package org.khasanof.core.tenancy.core.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.khasanof.core.tenancy.core.TenantContext;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.single.interceptor
 * @since 11/2/2024 2:17 PM
 */
public class TenantIdentifierInterceptor implements HandlerInterceptor {

    public static final String TENANT_ID = "X-Tenant-Id";

    /**
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tenantId = request.getHeader(TENANT_ID);
        if (tenantId != null) {
            TenantContext.setCurrentTenant(Long.valueOf(tenantId));
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}

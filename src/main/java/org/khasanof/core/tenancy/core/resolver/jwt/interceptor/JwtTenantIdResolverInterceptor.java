package org.khasanof.core.tenancy.core.resolver.jwt.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.khasanof.core.security.SecurityUtils;
import org.khasanof.core.tenancy.core.TenantContext;
import org.khasanof.core.tenancy.core.model.Tenant;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.core.resolver.jwt.interceptor
 * @since 7/13/2025 4:04 PM
 */
public class JwtTenantIdResolverInterceptor implements HandlerInterceptor {

    /**
     *
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler chosen handler to execute, for type and/or instance evaluation
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Optional<Long> currentUserTID = SecurityUtils.getCurrentUserTID();
        currentUserTID.ifPresent(tid -> TenantContext.setTenant(new Tenant(tid)));
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}

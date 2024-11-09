package org.khasanof.core.tenancy.core.resolver.condition;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.core.enumeration.TenancyResolverType;
import org.khasanof.core.tenancy.core.AbstractTenancyConfigurer;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.core.resolver
 * @since 11/9/2024 4:45 PM
 */
@Slf4j
public abstract class AbstractTenantResolverCondition extends AbstractTenancyConfigurer implements Condition {

    /**
     *
     * @param context
     * @param metadata
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        initializeTenancyConfigurer(context);
        return tenancyConfigurer != null && Objects.equals(tenancyConfigurer.getResolverType(), tenancyResolverType());
    }

    /**
     *
     * @return
     */
    public abstract TenancyResolverType tenancyResolverType();
}

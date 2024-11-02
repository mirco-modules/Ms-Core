package org.khasanof.core.tenancy.core.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.khasanof.core.enumeration.TenancyType;
import org.khasanof.core.tenancy.TenancyConfiguration;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.core.condition
 * @since 11/2/2024 3:28 PM
 */
public abstract class AbstractTenantCondition implements Condition {

    protected final TenancyConfiguration tenancyConfiguration;

    public AbstractTenantCondition(TenancyConfiguration tenancyConfiguration) {
        this.tenancyConfiguration = tenancyConfiguration;
    }

    /**
     *
     * @param context
     * @param metadata
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return Objects.equals(tenancyConfiguration.getType(), getTenancyType());
    }

    /**
     *
     * @return
     */
    public abstract TenancyType getTenancyType();
}

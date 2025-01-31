package org.khasanof.core.tenancy.core.condition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.khasanof.core.domain.enumeration.TenancyType;
import org.khasanof.core.tenancy.TenancyConfigurer;
import org.khasanof.core.tenancy.core.AbstractTenancyConfigurer;

import java.util.Objects;

/**
 * AbstractTenantCondition is an abstract base class for tenant-specific condition implementations.
 *
 * @author Nurislom
 * @see org.khasanof.core.tenancy.core.condition
 * @since 11/2/2024 3:28 PM
 */
@Slf4j
public abstract class AbstractTenantCondition extends AbstractTenancyConfigurer implements Condition {

    /**
     * Evaluates whether the condition matches based on the tenancy configuration and type.
     * This method initializes the {@link TenancyConfigurer} instance if it hasn't been set,
     * and checks whether the configured tenancy type matches the expected tenancy type.
     *
     * @param context  the condition context, providing access to the application context and environment
     * @param metadata metadata of the {@code @Conditional} annotation
     * @return {@code true} if the condition matches the configured tenancy type; {@code false} otherwise
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        initializeTenancyConfigurer(context);
        return tenancyConfigurer != null && Objects.equals(tenancyConfigurer.getType(), getTenancyType());
    }

    /**
     * Specifies the required {@link TenancyType} for this condition.
     * Subclasses should implement this method to define the tenancy type that they expect.
     *
     * @return the required tenancy type for this condition
     */
    public abstract TenancyType getTenancyType();
}

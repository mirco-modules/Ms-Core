package org.khasanof.core.tenancy.multi.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.multi.condition
 * @since 11/2/2024 3:27 PM
 */
@Component
public class MultiTenantCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return true;
    }
}

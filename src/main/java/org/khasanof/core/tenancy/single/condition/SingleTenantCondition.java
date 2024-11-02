package org.khasanof.core.tenancy.single.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof.core
 * @since 11/2/2024 3:21 PM
 */
@Component
public class SingleTenantCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return false;
    }
}

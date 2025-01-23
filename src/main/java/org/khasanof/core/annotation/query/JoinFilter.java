package org.khasanof.core.annotation.query;

import jakarta.persistence.criteria.JoinType;

import java.lang.annotation.*;

/**
 * @author Nurislom
 * @see org.khasanof.core.annotation.query
 * @since 12/7/2024 4:30 PM
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JoinFilter {

    String DEFAULT_REF_FIELD_NAME = "id";

    /**
     *
     * @return
     */
    String fieldName();

    /**
     *
     * @return
     */
    JoinType joinType() default JoinType.LEFT;

    /**
     *
     * @return
     */
    String referencedFieldName() default DEFAULT_REF_FIELD_NAME;
}

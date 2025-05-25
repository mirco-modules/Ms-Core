package org.khasanof.core.service.query.specification.builder;

import org.khasanof.core.annotation.Common;
import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.service.criteria.base.ICriteria;
import org.khasanof.core.service.query.specification.core.helper.CriteriaFieldResolver;
import org.khasanof.core.service.query.specification.core.manager.DynamicSpecificationBuilderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.query.specification.builder
 * @since 5/24/2025 2:45 PM
 */
@Component
public class DynamicSpecificationQueryBuilder {

    protected final CriteriaFieldResolver criteriaFieldResolver;
    protected final DynamicSpecificationBuilderManager dynamicSpecificationBuilderManager;

    public DynamicSpecificationQueryBuilder(CriteriaFieldResolver criteriaFieldResolver, DynamicSpecificationBuilderManager dynamicSpecificationBuilderManager) {
        this.criteriaFieldResolver = criteriaFieldResolver;
        this.dynamicSpecificationBuilderManager = dynamicSpecificationBuilderManager;
    }

    /**
     *
     * @param criteria
     * @return
     * @param <E>
     * @param <C>
     */
    public <E extends IEntity, C extends ICriteria> Specification<E> build(C criteria) {
        Specification<E> specification = Specification.where(null);
        Set<Field> criteriaFields = criteriaFieldResolver.resolveCriteriaFields(criteria);

        for (Field criteriaField : criteriaFields) {
            specification = buildFieldSpecification(specification, criteriaField, criteria);
        }
        return specification;
    }

    /**
     * Builds a {@link Specification} for a specific field in the criteria.
     *
     * @param specification The existing specification to extend.
     * @param field         The field to build a specification for.
     * @param criteria      The criteria object containing filter values.
     * @return The updated specification including the field's filter, if applicable.
     */
    private <E extends IEntity, C extends ICriteria> Specification<E> buildFieldSpecification(Specification<E> specification, Field field, C criteria) {
        try {
            if (!canAccessField(field)) {
                return specification;
            }

            Object value = field.get(criteria);
            if (value != null) {
                return dynamicSpecificationBuilderManager.build(specification, field, criteria);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to access field: " + field.getName(), e);
        }
        return specification;
    }

    /**
     * Determines whether a field can be accessed and sets it to accessible if possible.
     *
     * @param field The field to check.
     * @return {@code true} if the field can be accessed; {@code false} otherwise.
     */
    private boolean canAccessField(Field field) {
        try {
            return field.trySetAccessible();
        } catch (SecurityException e) {
            return false; // Log if necessary
        }
    }
}

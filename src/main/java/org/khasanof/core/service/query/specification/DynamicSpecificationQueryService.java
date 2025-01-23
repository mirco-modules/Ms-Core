package org.khasanof.core.service.query.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.repository.base.IGeneralRepository;
import org.khasanof.core.service.criteria.base.ICriteria;
import org.khasanof.core.service.dto.base.IDto;
import org.khasanof.core.service.mapper.base.IGeneralMapper;
import org.khasanof.core.service.query.base.GeneralQueryService;
import org.khasanof.core.service.query.specification.core.helper.CriteriaFieldResolver;
import org.khasanof.core.service.query.specification.core.manager.DynamicSpecificationBuilderManager;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.query.specification
 * @since 12/7/2024 4:23 PM
 */
@Service
public abstract class DynamicSpecificationQueryService<E extends IEntity, D extends IDto, C extends ICriteria> extends GeneralQueryService<E, D, C> {

    protected final CriteriaFieldResolver criteriaFieldResolver;
    protected final DynamicSpecificationBuilderManager dynamicSpecificationBuilderManager;

    public DynamicSpecificationQueryService(IGeneralMapper<E, D> generalMapper,
                                            IGeneralRepository<E> generalRepository,
                                            CriteriaFieldResolver criteriaFieldResolver,
                                            DynamicSpecificationBuilderManager dynamicSpecificationBuilderManager) {

        super(generalMapper, generalRepository);
        this.criteriaFieldResolver = criteriaFieldResolver;
        this.dynamicSpecificationBuilderManager = dynamicSpecificationBuilderManager;
    }

    /**
     * Function to convert {@link ICriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    @Override
    public Specification<E> createSpecification(C criteria) {
        Specification<E> specification = Specification.where(null);
        Set<Field> criteriaFields = criteriaFieldResolver.resolveCriteriaFields(criteria);
        preCreateSpecification(specification, criteria);

        for (Field criteriaField : criteriaFields) {
            specification = buildFieldSpecification(specification, criteriaField, criteria);
        }

        postCreateSpecification(specification, criteria);
        return specification;
    }

    /**
     * Hook for subclasses to add custom behavior before the specification is created.
     *
     * @param specification The initial specification (can be modified).
     * @param criteria      The criteria object containing filters.
     */
    protected void preCreateSpecification(Specification<E> specification, C criteria) {
    }

    /**
     * Hook for subclasses to add custom behavior after the specification is created.
     *
     * @param specification The final specification (can be modified).
     * @param criteria      The criteria object containing filters.
     */
    protected void postCreateSpecification(Specification<E> specification, C criteria) {
    }

    /**
     * Builds a {@link Specification} for a specific field in the criteria.
     *
     * @param specification The existing specification to extend.
     * @param field         The field to build a specification for.
     * @param criteria      The criteria object containing filter values.
     * @return The updated specification including the field's filter, if applicable.
     */
    private Specification<E> buildFieldSpecification(Specification<E> specification, Field field, C criteria) {
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

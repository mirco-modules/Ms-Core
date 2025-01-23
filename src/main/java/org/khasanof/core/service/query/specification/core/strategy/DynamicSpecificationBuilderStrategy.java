package org.khasanof.core.service.query.specification.core.strategy;

import org.springframework.data.jpa.domain.Specification;
import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.service.criteria.base.ICriteria;

import java.lang.reflect.Field;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.query.specification.core.strategy
 * @since 12/7/2024 4:45 PM
 */
public interface DynamicSpecificationBuilderStrategy {

    int DEFAULT_ORDER = 5;

    /**
     *
     * @param specification
     * @param field
     * @param criteriaInstance
     * @return
     * @param <T>
     */
    <T> Specification<T> build(Specification<T> specification, Field field, ICriteria criteriaInstance);

    /**
     *
     * @return
     */
    boolean isSupport(Field field);

    /**
     *
     * @return
     */
    default Integer order() {
        return DEFAULT_ORDER;
    }
}

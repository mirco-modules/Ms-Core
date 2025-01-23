package org.khasanof.core.service.query.specification.core.manager;

import org.springframework.data.jpa.domain.Specification;
import org.khasanof.core.service.criteria.base.ICriteria;

import java.lang.reflect.Field;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.query.specification.core.manager
 * @since 12/7/2024 6:07 PM
 */
public interface DynamicSpecificationBuilderManager {

    /**
     *
     * @param specification
     * @param field
     * @param criteriaInstance
     * @return
     * @param <T>
     */
    <T> Specification<T> build(Specification<T> specification, Field field, ICriteria criteriaInstance);
}

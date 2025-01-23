package org.khasanof.core.service.query.specification.core.strategy;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.khasanof.core.service.criteria.base.ICriteria;
import org.khasanof.core.service.query.specification.core.helper.QueryServiceHelper;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.query.specification.core.strategy
 * @since 12/7/2024 5:58 PM
 */
@Component
@SuppressWarnings({"rawtypes", "unchecked"})
public class DistinctFilterSpecificationBuilderStrategy extends AbstractDynamicSpecificationBuilderStrategy {

    public static final String DISTINCT = "distinct";

    public DistinctFilterSpecificationBuilderStrategy(QueryServiceHelper queryServiceHelper) {
        super(queryServiceHelper);
    }

    /**
     *
     * @param specification
     * @param field
     * @param criteriaInstance
     * @return
     * @param <T>
     */
    @Override
    public <T> Specification<T> build(Specification<T> specification, Field field, ICriteria criteriaInstance) {
        try {
            return tryBuild(specification, field, criteriaInstance);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param specification
     * @param field
     * @param criteriaInstance
     * @return
     * @param <T>
     * @throws IllegalAccessException
     */
    private <T> Specification tryBuild(Specification<T> specification, Field field, ICriteria criteriaInstance) throws IllegalAccessException {
        Boolean filter = getFilter(field, criteriaInstance);
        return specification.and(queryServiceHelper.distinct(filter));
    }

    /**
     *
     * @param field
     * @return
     */
    @Override
    public boolean isSupport(Field field) {
        return Boolean.class.isAssignableFrom(field.getType()) && Objects.equals(DISTINCT, field.getName());
    }

    /**
     *
     * @return
     */
    @Override
    public Integer order() {
        return 2;
    }
}

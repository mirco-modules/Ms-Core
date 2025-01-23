package org.khasanof.core.service.query.specification.core.strategy;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import tech.jhipster.service.filter.RangeFilter;
import org.khasanof.core.service.criteria.base.ICriteria;
import org.khasanof.core.service.query.specification.core.helper.QueryServiceHelper;

import java.lang.reflect.Field;
import java.util.function.Function;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.query.specification.core.strategy
 * @since 12/7/2024 4:49 PM
 */
@Component
@SuppressWarnings({"rawtypes", "unchecked"})
public class RangeFilterSpecificationBuilderStrategy extends AbstractDynamicSpecificationBuilderStrategy {

    public RangeFilterSpecificationBuilderStrategy(QueryServiceHelper queryServiceHelper) {
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
        RangeFilter rangeFilter = getFilter(field, criteriaInstance);
        Function<Root<T>, Expression> function = (tRoot -> tRoot.get(field.getName()));
        return specification.and(queryServiceHelper.buildSpecification(rangeFilter, function));
    }

    /**
     *
     * @param field
     * @return
     */
    @Override
    public boolean isSupport(Field field) {
        return RangeFilter.class.isAssignableFrom(field.getType());
    }
}

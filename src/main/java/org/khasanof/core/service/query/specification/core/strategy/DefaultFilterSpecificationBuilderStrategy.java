package org.khasanof.core.service.query.specification.core.strategy;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import tech.jhipster.service.filter.Filter;
import org.khasanof.core.service.criteria.base.ICriteria;
import org.khasanof.core.service.query.specification.core.helper.QueryServiceHelper;

import java.lang.reflect.Field;
import java.util.function.Function;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.query.specification.core.strategy
 * @since 12/7/2024 7:48 PM
 */
@Component
@SuppressWarnings({"rawtypes", "unchecked"})
public class DefaultFilterSpecificationBuilderStrategy extends AbstractDynamicSpecificationBuilderStrategy {

    public DefaultFilterSpecificationBuilderStrategy(QueryServiceHelper queryServiceHelper) {
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
        Filter filter = getFilter(field, criteriaInstance);
        Function<Root<T>, Expression> function = (tRoot -> tRoot.get(field.getName()));
        return specification.and(queryServiceHelper.buildSpecification(filter, function));
    }

    /**
     *
     * @param field
     * @return
     */
    @Override
    public boolean isSupport(Field field) {
        return Filter.class.isAssignableFrom(field.getType());
    }

    /**
     *
     * @return
     */
    @Override
    public Integer order() {
        return 10;
    }
}

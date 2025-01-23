package org.khasanof.core.service.query.specification.core.strategy;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import tech.jhipster.service.filter.Filter;
import org.khasanof.core.annotation.query.JoinFilter;
import org.khasanof.core.service.criteria.base.ICriteria;
import org.khasanof.core.service.query.specification.core.helper.QueryServiceHelper;

import java.lang.reflect.Field;
import java.util.function.Function;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.query.specification.core.strategy
 * @since 12/7/2024 6:38 PM
 */
@Component
@SuppressWarnings({"rawtypes", "unchecked"})
public class JoinFilterDynamicSpecificationBuilderStrategy extends AbstractDynamicSpecificationBuilderStrategy {

    public JoinFilterDynamicSpecificationBuilderStrategy(QueryServiceHelper queryServiceHelper) {
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

        JoinFilter joinFilter = field.getAnnotation(JoinFilter.class);
        if (joinFilter == null) {
            throw new RuntimeException("JoinFilter annotation not found");
        }

        Function<Root<T>, Expression> function = buildJoinExpression(joinFilter);
        return specification.and(queryServiceHelper.buildSpecification(filter, function));
    }

    /**
     *
     * @param joinFilter
     * @return
     * @param <T>
     */
    private <T> Function<Root<T>, Expression> buildJoinExpression(JoinFilter joinFilter) {
        return tRoot -> tRoot.join(joinFilter.fieldName(), joinFilter.joinType()).get(joinFilter.referencedFieldName());
    }

    /**
     *
     * @param field
     * @return
     */
    @Override
    public boolean isSupport(Field field) {
        return field.isAnnotationPresent(JoinFilter.class);
    }

    /**
     *
     * @return
     */
    @Override
    public Integer order() {
        return 1;
    }
}

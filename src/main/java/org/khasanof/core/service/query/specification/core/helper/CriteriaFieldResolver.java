package org.khasanof.core.service.query.specification.core.helper;

import org.springframework.stereotype.Component;
import tech.jhipster.service.filter.Filter;
import org.khasanof.core.annotation.query.IgnoreFilter;
import org.khasanof.core.service.criteria.base.ICriteria;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.khasanof.core.service.query.specification.core.strategy.DistinctFilterSpecificationBuilderStrategy.DISTINCT;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.query.specification.core.helper
 * @since 12/7/2024 4:35 PM
 */
@Component
public final class CriteriaFieldResolver {

    /**
     *
     * @param criteria
     * @return
     */
    public Set<Field> resolveCriteriaFields(ICriteria criteria) {
        Class<? extends ICriteria> criteriaClass = criteria.getClass();
        return Arrays.stream(criteriaClass.getDeclaredFields())
                .filter(field -> !field.isAnnotationPresent(IgnoreFilter.class))
                .filter(this::checkAllowedField)
                .collect(Collectors.toSet());
    }

    /**
     *
     * @param criteriaField
     * @return
     */
    private boolean checkAllowedField(Field criteriaField) {
        return Filter.class.isAssignableFrom(criteriaField.getType()) ||
                (Objects.equals(criteriaField.getType(), Boolean.class) && Objects.equals(criteriaField.getName(), DISTINCT));
    }
}

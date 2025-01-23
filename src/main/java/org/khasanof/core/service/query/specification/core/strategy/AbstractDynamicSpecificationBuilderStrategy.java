package org.khasanof.core.service.query.specification.core.strategy;

import org.khasanof.core.service.criteria.base.ICriteria;
import org.khasanof.core.service.query.specification.core.helper.QueryServiceHelper;

import java.lang.reflect.Field;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.query.specification.core.strategy
 * @since 12/7/2024 5:52 PM
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public abstract class AbstractDynamicSpecificationBuilderStrategy implements DynamicSpecificationBuilderStrategy {

    protected final QueryServiceHelper queryServiceHelper;

    public AbstractDynamicSpecificationBuilderStrategy(QueryServiceHelper queryServiceHelper) {
        this.queryServiceHelper = queryServiceHelper;
    }

    /**
     *
     * @param field
     * @param criteriaInstance
     * @return
     * @param <TYPE>
     * @throws IllegalAccessException
     */
    protected <TYPE> TYPE getFilter(Field field, ICriteria criteriaInstance) throws IllegalAccessException {
        if (!field.trySetAccessible()) {
            throw new IllegalAccessException();
        }
        return (TYPE) field.get(criteriaInstance);
    }
}

package org.khasanof.core.service.query.specification.core.manager;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.khasanof.core.service.criteria.base.ICriteria;
import org.khasanof.core.service.query.specification.core.strategy.DynamicSpecificationBuilderStrategy;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.query.specification.core.manager
 * @since 12/7/2024 6:11 PM
 */
@Component
public class DynamicSpecificationBuilderManagerImpl implements DynamicSpecificationBuilderManager, InitializingBean {

    private final ApplicationContext applicationContext;
    private final Set<DynamicSpecificationBuilderStrategy> strategies = new LinkedHashSet<>();

    public DynamicSpecificationBuilderManagerImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
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
        return strategies.stream()
                .filter(dynamicSpecificationBuilderStrategy -> dynamicSpecificationBuilderStrategy.isSupport(field))
                .findFirst()
                .map(dynamicSpecificationBuilderStrategy -> dynamicSpecificationBuilderStrategy.build(specification, field, criteriaInstance))
                .orElseThrow(() -> new RuntimeException("Specification strategy not found"));
    }

    /**
     *
     */
    @Override
    public void afterPropertiesSet() {
        strategies.addAll(getStrategies());
    }

    /**
     *
     * @return
     */
    private Set<DynamicSpecificationBuilderStrategy> getStrategies() {
        return applicationContext.getBeansOfType(DynamicSpecificationBuilderStrategy.class)
                .values().stream()
                .sorted(Comparator.comparing(DynamicSpecificationBuilderStrategy::order))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}

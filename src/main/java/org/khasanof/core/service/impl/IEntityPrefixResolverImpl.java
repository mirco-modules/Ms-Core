package org.khasanof.core.service.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.khasanof.core.service.IEntityPrefixResolver;
import org.khasanof.core.service.strategy.IEntityPrefixResolverStrategy;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.impl
 * @since 1/4/2025 5:23 PM
 */
@Service
public class IEntityPrefixResolverImpl implements IEntityPrefixResolver, InitializingBean {

    private final Set<IEntityPrefixResolverStrategy> strategies = new LinkedHashSet<>();

    private final ApplicationContext applicationContext;

    public IEntityPrefixResolverImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * @param beanDefinition
     * @return
     */
    @Override
    public String resolve(BeanDefinition beanDefinition) {
        return strategies.stream()
                .filter(iEntityPrefixResolverStrategy -> isSupportedEntityFilter(beanDefinition, iEntityPrefixResolverStrategy))
                .map(IEntityPrefixResolverStrategy::getEntityPrefix)
                .findFirst()
                .orElse(null);
    }

    /**
     *
     * @param beanDefinition
     * @param iEntityPrefixResolverStrategy
     * @return
     */
    private Boolean isSupportedEntityFilter(BeanDefinition beanDefinition, IEntityPrefixResolverStrategy iEntityPrefixResolverStrategy) {
        Class<?> entityClass = tryLoadEntityClass(beanDefinition);
        return iEntityPrefixResolverStrategy.isSupport(entityClass);
    }

    /**
     * @param beanDefinition
     * @return
     */
    private Class<?> tryLoadEntityClass(BeanDefinition beanDefinition) {
        try {
            return Class.forName(beanDefinition.getBeanClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        strategies.addAll(getEntityPrefixResolverStrategies());
    }

    /**
     * @return
     */
    private Collection<IEntityPrefixResolverStrategy> getEntityPrefixResolverStrategies() {
        return applicationContext.getBeansOfType(IEntityPrefixResolverStrategy.class)
                .values();
    }
}

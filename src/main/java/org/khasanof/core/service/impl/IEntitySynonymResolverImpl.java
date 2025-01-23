package org.khasanof.core.service.impl;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.khasanof.core.annotation.SynonymName;
import org.khasanof.core.service.IEntitySynonymResolver;

import static org.khasanof.core.util.BaseUtils.classLoader;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.impl
 * @since 1/7/2025 2:08 PM
 */
@Service
public class IEntitySynonymResolverImpl implements IEntitySynonymResolver {

    private final ApplicationContext applicationContext;

    public IEntitySynonymResolverImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     *
     * @param beanDefinition
     * @return
     */
    @Override
    public String resolve(BeanDefinition beanDefinition) {
        Class<?> beanClass = classLoader(beanDefinition.getBeanClassName(), applicationContext.getClassLoader());

        if (!beanClass.isAnnotationPresent(SynonymName.class)) {
            return beanClass.getSimpleName();
        }

        SynonymName synonymName = beanClass.getAnnotation(SynonymName.class);
        return synonymName.value();
    }
}

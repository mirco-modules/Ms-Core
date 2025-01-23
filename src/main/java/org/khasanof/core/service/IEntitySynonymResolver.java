package org.khasanof.core.service;

import org.springframework.beans.factory.config.BeanDefinition;

/**
 * @author Nurislom
 * @see org.khasanof.core.service
 * @since 1/7/2025 2:07 PM
 */
public interface IEntitySynonymResolver {

    /**
     *
     * @param beanDefinition
     * @return
     */
    String resolve(BeanDefinition beanDefinition);
}

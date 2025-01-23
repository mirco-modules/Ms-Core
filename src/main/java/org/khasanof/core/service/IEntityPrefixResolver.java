package org.khasanof.core.service;

import org.springframework.beans.factory.config.BeanDefinition;

/**
 * @author Nurislom
 * @see org.khasanof.core.service
 * @since 1/4/2025 5:23 PM
 */
public interface IEntityPrefixResolver {

    /**
     *
     * @param beanDefinition
     * @return
     */
    String resolve(BeanDefinition beanDefinition);
}

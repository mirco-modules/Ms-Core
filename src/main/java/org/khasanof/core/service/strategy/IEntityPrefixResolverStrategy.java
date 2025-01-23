package org.khasanof.core.service.strategy;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.strategy
 * @since 1/4/2025 5:24 PM
 */
public interface IEntityPrefixResolverStrategy {

    /**
     *
     * @return
     */
    String getEntityPrefix();

    /**
     *
     * @param entityClass
     * @return
     */
    Boolean isSupport(Class<?> entityClass);
}

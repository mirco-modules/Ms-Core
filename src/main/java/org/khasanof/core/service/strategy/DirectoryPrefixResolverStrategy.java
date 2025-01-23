package org.khasanof.core.service.strategy;

import org.springframework.stereotype.Component;
import org.khasanof.core.constants.DbTypesConstants;
import org.khasanof.core.domain.types.IDirectory;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.strategy
 * @since 1/4/2025 5:25 PM
 */
@Component
public class DirectoryPrefixResolverStrategy implements IEntityPrefixResolverStrategy {

    /**
     *
     * @return
     */
    @Override
    public String getEntityPrefix() {
        return DbTypesConstants.DIRECTORY_PREFIX;
    }

    /**
     *
     * @param entityClass
     * @return
     */
    @Override
    public Boolean isSupport(Class<?> entityClass) {
        return IDirectory.class.isAssignableFrom(entityClass);
    }
}

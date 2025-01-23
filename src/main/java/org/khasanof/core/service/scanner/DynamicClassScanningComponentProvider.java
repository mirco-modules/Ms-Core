package org.khasanof.core.service.scanner;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.core.type.filter.TypeFilter;

import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.scanner
 * @since 12/11/2024 4:48 PM
 */
public interface DynamicClassScanningComponentProvider {

    /**
     *
     * @param typeFilter
     * @return
     */
    Set<BeanDefinition> findComponents(TypeFilter typeFilter);
}

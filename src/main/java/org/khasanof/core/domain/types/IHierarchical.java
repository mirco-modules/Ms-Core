package org.khasanof.core.domain.types;

/**
 * @author Nurislom
 * @see org.khasanof.core.domain.types
 * @since 1/22/2025 1:43 PM
 */
public interface IHierarchical<T> extends IIdentified, INamed {

    /**
     *
     * @return
     */
    T getParent();

    /**
     *
     * @param parent
     */
    void setParent(T parent);
}

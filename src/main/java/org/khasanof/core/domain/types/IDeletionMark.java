package org.khasanof.core.domain.types;

/**
 * @author Nurislom
 * @see org.khasanof.core.domain.types
 * @since 12/23/2024 2:57 PM
 */
public interface IDeletionMark {

    /**
     *
     * @return
     */
    boolean isDeleted();

    /**
     *
     * @param isDeleted
     */
    void isDeleted(boolean isDeleted);
}

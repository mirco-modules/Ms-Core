package org.khasanof.core.service.validator;

import org.khasanof.core.service.dto.base.IDto;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.validator
 * @since 12/19/2024 12:22 PM
 */
public interface IGeneralValidator<D extends IDto> {

    /**
     *
     */
    default void preSave(D dto) {}

    /**
     *
     * @param dto
     */
    default void preUpdate(D dto) {}

    /**
     *
     * @param dto
     */
    default void preDelete(D dto) {}

    /**
     *
     * @param dto
     * @return
     */
    boolean support(D dto);
}

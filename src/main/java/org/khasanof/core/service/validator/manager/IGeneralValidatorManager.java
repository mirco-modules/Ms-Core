package org.khasanof.core.service.validator.manager;

import org.khasanof.core.service.dto.base.IDto;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.validator.manager
 * @since 12/19/2024 3:35 PM
 */
public interface IGeneralValidatorManager {

    /**
     *
     * @param dto
     */
    void preSave(IDto dto);

    /**
     *
     * @param dto
     */
    void preUpdate(IDto dto);

    /**
     *
     * @param id
     */
    void preDelete(Long id);
}

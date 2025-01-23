package org.khasanof.core.service.base;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.base
 * @since 12/25/2024 1:54 PM
 */
public interface IGeneralSoftDeleteService {

    /**
     *
     * @param id
     */
    Boolean softDelete(Long id);
}

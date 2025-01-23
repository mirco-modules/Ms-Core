package org.khasanof.core.service.base;

import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.service.dto.base.IDto;

import java.util.List;
import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.base
 * @since 12/4/2024 6:11 PM
 */
public interface IGeneralMultiTenancyService<E extends IEntity, D extends IDto> {

    /**
     *
     * @return
     */
    List<D> getEntities();

    /**
     *
     * @param id
     * @return
     */
    Optional<D> getEntityById(Long id);

    /**
     *
     * @param dto
     * @return
     */
    D submit(D dto);

    /**
     *
     * @param id
     * @return
     */
    Boolean delete(Long id);
}

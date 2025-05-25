package org.khasanof.core.service.base;

import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.service.dto.base.IDto;

import java.util.List;
import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.base
 * @since 12/4/2024 6:08 PM
 */
public interface IGeneralService<E extends IEntity, D extends IDto> {

    /**
     * Get all entities
     *
     * @return the entities
     */
    List<D> getAll();

    /**
     * Get the "id" entity.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<D> getById(Long id);

    /**
     * Save or update a entity
     *
     * @param dto the entity to save.
     * @return the persisted entity.
     */
    D submit(D dto);

    /**
     * Delete the "id" entity.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

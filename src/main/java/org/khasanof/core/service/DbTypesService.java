package org.khasanof.core.service;

import org.khasanof.core.service.dto.DbTypesDTO;

import java.util.Optional;

/**
 * Service Interface for managing {@link org.khasanof.core.domain.DbTypes}.
 */
public interface DbTypesService {
    /**
     * Save a dbTypes.
     *
     * @param dbTypesDTO the entity to save.
     * @return the persisted entity.
     */
    DbTypesDTO save(DbTypesDTO dbTypesDTO);

    /**
     * Updates a dbTypes.
     *
     * @param dbTypesDTO the entity to update.
     * @return the persisted entity.
     */
    DbTypesDTO update(DbTypesDTO dbTypesDTO);

    /**
     * Partially updates a dbTypes.
     *
     * @param dbTypesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DbTypesDTO> partialUpdate(DbTypesDTO dbTypesDTO);

    /**
     * Get the "id" dbTypes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DbTypesDTO> findOne(Long id);

    /**
     * Delete the "id" dbTypes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

package org.khasanof.core.service;

import org.khasanof.core.service.dto.VariablesDTO;

import java.util.Optional;

/**
 * Service Interface for managing {@link org.khasanof.core.domain.Variables}.
 */
public interface VariablesService {
    /**
     * Save a variables.
     *
     * @param variablesDTO the entity to save.
     * @return the persisted entity.
     */
    VariablesDTO save(VariablesDTO variablesDTO);

    /**
     * Updates a variables.
     *
     * @param variablesDTO the entity to update.
     * @return the persisted entity.
     */
    VariablesDTO update(VariablesDTO variablesDTO);

    /**
     * Partially updates a variables.
     *
     * @param variablesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<VariablesDTO> partialUpdate(VariablesDTO variablesDTO);

    /**
     * Get the "id" variables.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VariablesDTO> findOne(Long id);

    /**
     * Delete the "id" variables.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

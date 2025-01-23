package org.khasanof.core.web.rest.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import tech.jhipster.web.util.ResponseUtil;
import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.service.base.IGeneralService;
import org.khasanof.core.service.dto.base.IDto;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.core.web.rest.base
 * @since 12/4/2024 6:57 PM
 */
@Slf4j
public abstract class GeneralResource<E extends IEntity, D extends IDto> implements IGeneralResource<D> {

    protected final IGeneralService<E, D> generalService;

    public GeneralResource(IGeneralService<E, D> generalService) {
        this.generalService = generalService;
    }

    /**
     * Get the "id" entity.
     *
     * @param id the id of the entity dto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entity dto, or with status {@code 404 (Not Found)}.
     */
    @Override
    public ResponseEntity<D> getById(Long id) {
        log.debug("REST request to get : {}", id);
        Optional<D> optionalEntity = generalService.getById(id);
        return ResponseUtil.wrapOrNotFound(optionalEntity);
    }

    /**
     * Save or update the entity.
     *
     * @param dto the entity dto to save or update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entity dto
     */
    @Override
    public ResponseEntity<D> submit(D dto) {
        log.debug("REST request to submit : {}", dto);
        D response = generalService.submit(dto);
        return ResponseEntity.ok(response);
    }

    /**
     * Delete the "id" entity.
     *
     * @param id the id of the entity dto to delete.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)}.
     */
    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        log.debug("REST request to delete : {}", id);
        Boolean deleted = generalService.delete(id);
        return ResponseEntity.ok(deleted);
    }
}

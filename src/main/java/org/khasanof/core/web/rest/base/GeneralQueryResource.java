package org.khasanof.core.web.rest.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;
import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.service.base.IGeneralService;
import org.khasanof.core.service.criteria.base.ICriteria;
import org.khasanof.core.service.dto.base.IDto;
import org.khasanof.core.service.query.base.IGeneralQueryService;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.core.web.rest.base
 * @since 12/5/2024 11:02 AM
 */
@Slf4j
public abstract class GeneralQueryResource<E extends IEntity, D extends IDto, C extends ICriteria> extends GeneralResource<E, D> implements IGeneralQueryResource<D, C> {

    private final IGeneralQueryService<E, D, C> generalQueryService;

    public GeneralQueryResource(IGeneralService<E, D> generalService, IGeneralQueryService<E, D, C> generalQueryService) {
        super(generalService);
        this.generalQueryService = generalQueryService;
    }

    /**
     * Get all the entities.
     *
     * @param criteria the pagination information.
     * @param pageable the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entities in body.
     */
    @Override
    public ResponseEntity<List<D>> getAll(C criteria, Pageable pageable) {
        log.debug("REST request to get by criteria: {}", criteria);
        Page<D> page = generalQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * Count all the entities.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @Override
    public ResponseEntity<Long> count(C criteria) {
        log.debug("REST request to count by criteria: {}", criteria);
        return ResponseEntity.ok().body(generalQueryService.countByCriteria(criteria));
    }
}

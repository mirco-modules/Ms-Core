package org.khasanof.core.web.rest.base.catalog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.khasanof.core.domain.types.IDeletionMark;
import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.service.base.ICatalogService;
import org.khasanof.core.service.criteria.base.ICriteria;
import org.khasanof.core.service.dto.base.IDto;
import org.khasanof.core.service.query.base.IGeneralQueryService;
import org.khasanof.core.web.rest.base.GeneralQueryResource;
import org.khasanof.core.web.rest.base.IGeneralSoftDeleteResource;

/**
 * @author Nurislom
 * @see org.khasanof.core.web.rest.base.catalog
 * @since 12/25/2024 3:44 PM
 */
@Slf4j
public abstract class CatalogResource<E extends IEntity & IDeletionMark, D extends IDto, C extends ICriteria> extends GeneralQueryResource<E, D, C> implements IGeneralSoftDeleteResource {

    private final ICatalogService<E, D> catalogService;

    public CatalogResource(ICatalogService<E, D> catalogService, IGeneralQueryService<E, D, C> generalQueryService) {
        super(catalogService, generalQueryService);
        this.catalogService = catalogService;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<Boolean> softDelete(Long id) {
        log.debug("REST request to soft delete : {}", id);
        return ResponseEntity.ok(catalogService.softDelete(id));
    }
}

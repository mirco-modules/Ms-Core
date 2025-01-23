package org.khasanof.core.web.rest.base.hierarchy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.domain.types.IHierarchical;
import org.khasanof.core.service.base.IHierarchicalService;
import org.khasanof.core.service.criteria.base.ICriteria;
import org.khasanof.core.service.dto.HierarchicalDTO;
import org.khasanof.core.service.dto.base.IDto;
import org.khasanof.core.service.query.base.IGeneralQueryService;
import org.khasanof.core.web.rest.base.GeneralQueryResource;
import org.khasanof.core.web.rest.base.IHierarchicalResource;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.core.web.rest.base.hierarchy
 * @since 1/22/2025 3:30 PM
 */
@Slf4j
public abstract class HierarchicalResource<E extends IEntity & IHierarchical, D extends IDto, C extends ICriteria> extends GeneralQueryResource<E, D, C> implements IHierarchicalResource {

    protected final IHierarchicalService<E, D> hierarchicalService;

    public HierarchicalResource(IHierarchicalService<E, D> hierarchicalService, IGeneralQueryService<E, D, C> generalQueryService) {
        super(hierarchicalService, generalQueryService);
        this.hierarchicalService = hierarchicalService;
    }

    /**
     *
     * @return
     */
    @Override
    public ResponseEntity<List<HierarchicalDTO>> getAllHierarchical() {
        log.debug("REST request to get all hierarchical");
        return ResponseEntity.ok(hierarchicalService.getAllByHierarchical());
    }
}

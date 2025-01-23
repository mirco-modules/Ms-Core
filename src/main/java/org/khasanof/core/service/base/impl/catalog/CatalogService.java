package org.khasanof.core.service.base.impl.catalog;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.core.domain.types.IDeletionMark;
import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.repository.base.catalog.ICatalogRepository;
import org.khasanof.core.service.base.ICatalogService;
import org.khasanof.core.service.base.impl.GeneralValidateService;
import org.khasanof.core.service.dto.base.IDto;
import org.khasanof.core.service.mapper.base.IGeneralMapper;
import org.khasanof.core.service.validator.manager.GeneralValidatorManager;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.base.impl.catalog
 * @since 12/25/2024 3:13 PM
 */
@Slf4j
public abstract class CatalogService<E extends IEntity & IDeletionMark, D extends IDto> extends GeneralValidateService<E, D> implements ICatalogService<E, D> {

    private final ICatalogRepository<E> catalogRepository;

    public CatalogService(IGeneralMapper<E, D> generalMapper,
                          ICatalogRepository<E> catalogRepository,
                          GeneralValidatorManager generalValidatorManager
    ) {
        super(generalMapper, catalogRepository, generalValidatorManager);
        this.catalogRepository = catalogRepository;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Boolean softDelete(Long id) {
        log.debug("Request to soft delete : {}", id);
        preSoftDelete(id);

        if (!generalRepository.existsById(id)) {
            log.warn("Entity not found by id : {}", id);
            return Boolean.FALSE;
        }

        postSoftDelete(id);
        catalogRepository.softDeleteById(id);
        return Boolean.TRUE;
    }

    /**
     *
     * @param id
     */
    protected void preSoftDelete(Long id) {
    }

    /**
     *
     * @param id
     */
    protected void postSoftDelete(Long id) {
    }
}

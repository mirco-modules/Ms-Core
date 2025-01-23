package org.khasanof.core.service.base.impl;

import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.repository.base.IGeneralRepository;
import org.khasanof.core.service.dto.base.IDto;
import org.khasanof.core.service.mapper.base.IGeneralMapper;
import org.khasanof.core.service.validator.manager.IGeneralValidatorManager;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.base.impl
 * @since 12/19/2024 2:30 PM
 */
public abstract class GeneralValidateService<E extends IEntity, D extends IDto> extends GeneralService<E, D> {

    private final IGeneralValidatorManager generalValidatorManager;

    public GeneralValidateService(IGeneralMapper<E, D> generalMapper, IGeneralRepository<E> generalRepository, IGeneralValidatorManager generalValidatorManager) {
        super(generalMapper, generalRepository);
        this.generalValidatorManager = generalValidatorManager;
    }

    /**
     *
     * @param dto
     */
    @Override
    protected void preSave(D dto) {
        super.preSave(dto);
        generalValidatorManager.preSave(dto);
    }

    /**
     *
     * @param dto
     */
    @Override
    protected void preUpdate(D dto) {
        super.preUpdate(dto);
        generalValidatorManager.preUpdate(dto);
    }
}

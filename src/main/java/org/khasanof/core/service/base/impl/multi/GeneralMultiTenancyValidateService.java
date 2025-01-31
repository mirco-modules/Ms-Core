package org.khasanof.core.service.base.impl.multi;

import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.repository.base.multi.IGeneralMultiTenancyRepository;
import org.khasanof.core.service.dto.base.IDto;
import org.khasanof.core.service.mapper.base.IGeneralMapper;
import org.khasanof.core.service.validator.manager.IGeneralValidatorManager;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.base.impl.multi
 * @since 1/27/2025 11:30 AM
 */
public abstract class GeneralMultiTenancyValidateService<E extends IEntity, D extends IDto> extends GeneralMultiTenancyService<E, D> {

    protected final IGeneralValidatorManager generalValidatorManager;

    public GeneralMultiTenancyValidateService(IGeneralMapper<E, D> generalMapper,
                                              IGeneralMultiTenancyRepository<E> generalMultiTenancyRepository,
                                              IGeneralValidatorManager generalValidatorManager
    ) {
        super(generalMapper, generalMultiTenancyRepository);
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

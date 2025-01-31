package org.khasanof.core.service.base.impl.multi;

import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.repository.base.IGeneralRepository;
import org.khasanof.core.repository.base.multi.IGeneralMultiTenancyRepository;
import org.khasanof.core.service.base.impl.GeneralService;
import org.khasanof.core.service.dto.base.IDto;
import org.khasanof.core.service.mapper.base.IGeneralMapper;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.base.impl
 * @since 12/4/2024 6:40 PM
 */
public abstract class GeneralMultiTenancyService<E extends IEntity, D extends IDto> extends GeneralService<E, D> {

    public GeneralMultiTenancyService(IGeneralMapper<E, D> generalMapper, IGeneralMultiTenancyRepository<E> generalMultiTenancyRepository) {
        super(generalMapper, generalMultiTenancyRepository);
    }
}

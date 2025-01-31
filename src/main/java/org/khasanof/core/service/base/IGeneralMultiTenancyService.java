package org.khasanof.core.service.base;

import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.service.dto.base.IDto;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.base
 * @since 12/4/2024 6:11 PM
 */
public interface IGeneralMultiTenancyService<E extends IEntity, D extends IDto> extends IGeneralService<E, D> {
}

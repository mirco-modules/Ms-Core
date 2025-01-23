package org.khasanof.core.service.mapper.base;

import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.service.dto.base.IDto;
import org.khasanof.core.service.mapper.EntityMapper;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.mapper
 * @since 12/4/2024 6:09 PM
 */
public interface IGeneralMapper<E extends IEntity, D extends IDto> extends EntityMapper<D, E> {
}

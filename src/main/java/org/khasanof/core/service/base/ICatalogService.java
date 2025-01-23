package org.khasanof.core.service.base;

import org.khasanof.core.domain.types.IDeletionMark;
import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.service.dto.base.IDto;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.base
 * @since 12/25/2024 1:53 PM
 */
public interface ICatalogService<E extends IEntity & IDeletionMark, D extends IDto> extends IGeneralService<E, D>, IGeneralSoftDeleteService {
}

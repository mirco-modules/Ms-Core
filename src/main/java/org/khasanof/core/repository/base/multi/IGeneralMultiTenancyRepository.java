package org.khasanof.core.repository.base.multi;

import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.repository.base.IGeneralRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Nurislom
 * @see org.khasanof.core.repository.base
 * @since 12/4/2024 5:32 PM
 */
@NoRepositoryBean
public interface IGeneralMultiTenancyRepository<E extends IEntity> extends IGeneralRepository<E> {
}

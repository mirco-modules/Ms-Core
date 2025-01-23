package org.khasanof.core.repository.base.multi;

import org.springframework.data.repository.NoRepositoryBean;
import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.tenancy.core.repository.MultitenancyRepository;

/**
 * @author Nurislom
 * @see org.khasanof.core.repository.base
 * @since 12/4/2024 5:32 PM
 */
@NoRepositoryBean
public interface IGeneralMultiTenancyRepository<T extends IEntity> extends MultitenancyRepository<T, Long> {
}

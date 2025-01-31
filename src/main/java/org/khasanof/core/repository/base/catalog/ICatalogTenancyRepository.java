package org.khasanof.core.repository.base.catalog;

import org.springframework.data.repository.NoRepositoryBean;
import org.khasanof.core.domain.types.IDeletionMark;
import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.repository.base.multi.IGeneralMultiTenancyRepository;

/**
 * @author Nurislom
 * @see org.khasanof.core.repository.base.catalog
 * @since 1/23/2025 6:15 PM
 */
@NoRepositoryBean
public interface ICatalogTenancyRepository<E extends IEntity & IDeletionMark> extends ICatalogRepository<E>, IGeneralMultiTenancyRepository<E> {
}

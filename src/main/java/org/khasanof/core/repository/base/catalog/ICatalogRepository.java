package org.khasanof.core.repository.base.catalog;

import org.springframework.data.repository.NoRepositoryBean;
import org.khasanof.core.domain.types.IDeletionMark;
import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.repository.base.IGeneralRepository;
import org.khasanof.core.repository.base.IGeneralSoftDeleteRepository;

/**
 * @author Nurislom
 * @see org.khasanof.core.repository.base
 * @since 12/25/2024 1:42 PM
 */
@NoRepositoryBean
public interface ICatalogRepository<E extends IEntity & IDeletionMark> extends IGeneralRepository<E>, IGeneralSoftDeleteRepository<E> {
}

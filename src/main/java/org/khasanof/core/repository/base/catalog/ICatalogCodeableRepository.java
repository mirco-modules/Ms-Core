package org.khasanof.core.repository.base.catalog;

import org.springframework.data.repository.NoRepositoryBean;
import org.khasanof.core.domain.types.ICodeable;
import org.khasanof.core.domain.types.IDeletionMark;
import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.repository.base.IGeneralCodeableRepository;

/**
 * @author Nurislom
 * @see org.khasanof.core.repository.base.catalog
 * @since 12/25/2024 3:11 PM
 */
@NoRepositoryBean
public interface ICatalogCodeableRepository<E extends IEntity & IDeletionMark & ICodeable> extends ICatalogRepository<E>, IGeneralCodeableRepository<E> {
}

package org.khasanof.core.repository.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.khasanof.core.domain.types.IEntity;

/**
 * @author Nurislom
 * @see org.khasanof.core.repository.base
 * @since 12/4/2024 5:31 PM
 */
@NoRepositoryBean
public interface IGeneralRepository<E extends IEntity> extends JpaRepository<E, Long>, JpaSpecificationExecutor<E> {
}

package org.khasanof.core.tenancy.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Nurislom
 * @see org.khasanof.core.tenancy.core.repository
 * @since 11/9/2024 12:01 PM
 */
@NoRepositoryBean
public interface MultitenancyRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}

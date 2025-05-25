package org.khasanof.core.repository.common;

import org.khasanof.core.domain.common.Variables;
import org.khasanof.core.repository.base.IGeneralRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the Variables entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VariablesRepository extends IGeneralRepository<Variables> {

    /**
     *
     * @param name
     * @return
     */
    boolean existsByName(String name);

    /**
     *
     * @param name
     * @return
     */
    Optional<Variables> findByName(String name);
}

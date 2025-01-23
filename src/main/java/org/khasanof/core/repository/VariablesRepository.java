package org.khasanof.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.khasanof.core.domain.Variables;

import java.util.Optional;

/**
 * Spring Data JPA repository for the Variables entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VariablesRepository extends JpaRepository<Variables, Long>, JpaSpecificationExecutor<Variables> {

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

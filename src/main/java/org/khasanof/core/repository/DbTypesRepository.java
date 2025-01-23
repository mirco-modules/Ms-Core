package org.khasanof.core.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.khasanof.core.domain.DbTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the DbTypes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DbTypesRepository extends JpaRepository<DbTypes, Long>, JpaSpecificationExecutor<DbTypes> {

    /**
     *
     * @param name
     * @return
     */
    boolean existsByName(@NotNull String name);

    /**
     *
     * @param name
     * @return
     */
    Optional<DbTypes> findByName(@NotNull String name);
}

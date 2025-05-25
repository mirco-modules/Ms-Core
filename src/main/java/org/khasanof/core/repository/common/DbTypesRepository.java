package org.khasanof.core.repository.common;

import jakarta.validation.constraints.NotNull;
import org.khasanof.core.domain.common.DbTypes;
import org.khasanof.core.repository.base.IGeneralRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the DbTypes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DbTypesRepository extends IGeneralRepository<DbTypes> {

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

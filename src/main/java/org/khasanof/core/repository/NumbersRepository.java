package org.khasanof.core.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Repository;
import org.khasanof.core.domain.DbTypes;
import org.khasanof.core.domain.Numbers;
import org.khasanof.core.repository.base.IGeneralRepository;

import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.core.repository
 * @since 12/11/2024 2:02 PM
 */
@Repository
public interface NumbersRepository extends IGeneralRepository<Numbers> {

    /**
     *
     * @param dbType
     * @return
     */
    boolean existsByDbType(@NotNull DbTypes dbType);

    /**
     *
     * @param dbType_name
     * @return
     */
    Optional<Numbers> findByDbType_Name(@NotNull String dbType_name);
}

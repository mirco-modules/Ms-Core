package org.khasanof.core.repository.common;

import jakarta.validation.constraints.NotNull;
import org.khasanof.core.domain.common.DbTypes;
import org.khasanof.core.domain.common.Numbers;
import org.khasanof.core.repository.base.IGeneralRepository;
import org.springframework.stereotype.Repository;

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

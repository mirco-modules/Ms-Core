package org.khasanof.core.repository.base;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.khasanof.core.domain.types.IDeletionMark;
import org.khasanof.core.domain.types.IEntity;

/**
 * @author Nurislom
 * @see org.khasanof.core.repository.base
 * @since 12/23/2024 3:21 PM
 */
@NoRepositoryBean
public interface IGeneralSoftDeleteRepository<E extends IEntity & IDeletionMark> extends IGeneralRepository<E> {

    /**
     * @param id
     */
    @Modifying
    @Query("UPDATE #{#entityName} t SET t.isDeleted = true WHERE t.id = :id")
    void softDeleteById(Long id);
}

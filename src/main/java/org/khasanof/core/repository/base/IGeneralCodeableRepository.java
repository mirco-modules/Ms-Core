package org.khasanof.core.repository.base;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.khasanof.core.domain.types.ICodeable;
import org.khasanof.core.domain.types.IEntity;

/**
 * @author Nurislom
 * @see org.khasanof.core.repository.base
 * @since 12/4/2024 5:32 PM
 */
@NoRepositoryBean
public interface IGeneralCodeableRepository<E extends IEntity & ICodeable> extends IGeneralRepository<E> {

    /**
     *
     * @param code
     * @return
     */
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN TRUE ELSE FALSE END FROM #{#entityName} t WHERE t.code = :code")
    boolean existByCode(Integer code);
}

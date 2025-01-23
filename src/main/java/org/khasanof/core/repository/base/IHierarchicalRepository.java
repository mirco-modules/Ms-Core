package org.khasanof.core.repository.base;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.domain.types.IHierarchical;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.core.repository.base
 * @since 1/22/2025 1:46 PM
 */
@NoRepositoryBean
public interface IHierarchicalRepository<E extends IEntity & IHierarchical> extends IGeneralRepository<E> {

    /**
     *
     * @return
     */
    @Query("FROM #{#entityName} t WHERE t.parent IS NULL")
    List<E> findByParentIsNull();

    /**
     *
     * @param parentId
     * @return
     */
    @Query("FROM #{#entityName} t WHERE t.parent.id = :parentId")
    List<E> findChildrenByParentId(Long parentId);
}

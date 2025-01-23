package org.khasanof.core.service.query.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.service.criteria.base.ICriteria;
import org.khasanof.core.service.dto.base.IDto;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.query.base
 * @since 12/5/2024 10:49 AM
 */
public interface IGeneralQueryService<E extends IEntity, D extends IDto, C extends ICriteria> {

    /**
     * Return a {@link Page} of {@link IEntity} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    Page<D> findByCriteria(C criteria, Pageable page);

    /**
     * the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    long countByCriteria(C criteria);

    /**
     * Function to convert {@link ICriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    Specification<E> createSpecification(C criteria);
}

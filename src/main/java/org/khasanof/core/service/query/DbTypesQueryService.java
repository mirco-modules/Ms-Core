package org.khasanof.core.service.query;

import org.khasanof.core.domain.*;
import org.khasanof.core.domain.DbTypes;
import org.khasanof.core.repository.DbTypesRepository;
import org.khasanof.core.service.criteria.DbTypesCriteria;
import org.khasanof.core.service.dto.DbTypesDTO;
import org.khasanof.core.service.mapper.DbTypesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link DbTypes} entities in the database.
 * The main input is a {@link DbTypesCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DbTypesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DbTypesQueryService extends QueryService<DbTypes> {

    private final Logger log = LoggerFactory.getLogger(DbTypesQueryService.class);

    private final DbTypesRepository dbTypesRepository;

    private final DbTypesMapper dbTypesMapper;

    public DbTypesQueryService(DbTypesRepository dbTypesRepository, DbTypesMapper dbTypesMapper) {
        this.dbTypesRepository = dbTypesRepository;
        this.dbTypesMapper = dbTypesMapper;
    }

    /**
     * Return a {@link Page} of {@link DbTypesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DbTypesDTO> findByCriteria(DbTypesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DbTypes> specification = createSpecification(criteria);
        return dbTypesRepository.findAll(specification, page).map(dbTypesMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DbTypesCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DbTypes> specification = createSpecification(criteria);
        return dbTypesRepository.count(specification);
    }

    /**
     * Function to convert {@link DbTypesCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DbTypes> createSpecification(DbTypesCriteria criteria) {
        Specification<DbTypes> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DbTypes_.id));
            }
            if (criteria.getPrefix() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPrefix(), DbTypes_.prefix));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), DbTypes_.name));
            }
            if (criteria.getSynonym() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSynonym(), DbTypes_.synonym));
            }
            if (criteria.getIsPrimitive() != null) {
                specification = specification.and(buildSpecification(criteria.getIsPrimitive(), DbTypes_.isPrimitive));
            }
        }
        return specification;
    }
}

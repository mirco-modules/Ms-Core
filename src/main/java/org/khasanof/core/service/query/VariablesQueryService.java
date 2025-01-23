package org.khasanof.core.service.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;
import org.khasanof.core.domain.*;
import org.khasanof.core.domain.Variables;
import org.khasanof.core.repository.VariablesRepository;
import org.khasanof.core.service.criteria.VariablesCriteria;
import org.khasanof.core.service.dto.VariablesDTO;
import org.khasanof.core.service.mapper.VariablesMapper;

/**
 * Service for executing complex queries for {@link Variables} entities in the database.
 * The main input is a {@link VariablesCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link VariablesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class VariablesQueryService extends QueryService<Variables> {

    private static final Logger LOG = LoggerFactory.getLogger(VariablesQueryService.class);

    private final VariablesRepository variablesRepository;

    private final VariablesMapper variablesMapper;

    public VariablesQueryService(VariablesRepository variablesRepository, VariablesMapper variablesMapper) {
        this.variablesRepository = variablesRepository;
        this.variablesMapper = variablesMapper;
    }

    /**
     * Return a {@link Page} of {@link VariablesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<VariablesDTO> findByCriteria(VariablesCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Variables> specification = createSpecification(criteria);
        return variablesRepository.findAll(specification, page).map(variablesMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(VariablesCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<Variables> specification = createSpecification(criteria);
        return variablesRepository.count(specification);
    }

    /**
     * Function to convert {@link VariablesCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Variables> createSpecification(VariablesCriteria criteria) {
        Specification<Variables> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Variables_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Variables_.name));
            }
            if (criteria.getValue() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValue(), Variables_.value));
            }
        }
        return specification;
    }
}

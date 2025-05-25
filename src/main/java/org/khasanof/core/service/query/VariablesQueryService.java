package org.khasanof.core.service.query;

import org.khasanof.core.repository.base.IGeneralRepository;
import org.khasanof.core.service.mapper.base.IGeneralMapper;
import org.khasanof.core.service.query.specification.DynamicSpecificationQueryService;
import org.khasanof.core.service.query.specification.core.helper.CriteriaFieldResolver;
import org.khasanof.core.service.query.specification.core.manager.DynamicSpecificationBuilderManager;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.khasanof.core.domain.common.Variables;
import org.khasanof.core.service.criteria.VariablesCriteria;
import org.khasanof.core.service.dto.VariablesDTO;

/**
 * Service for executing complex queries for {@link Variables} entities in the database.
 * The main input is a {@link VariablesCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link VariablesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class VariablesQueryService extends DynamicSpecificationQueryService<Variables, VariablesDTO, VariablesCriteria> {

    public VariablesQueryService(IGeneralMapper<Variables, VariablesDTO> generalMapper,
                                 IGeneralRepository<Variables> generalRepository,
                                 CriteriaFieldResolver criteriaFieldResolver,
                                 DynamicSpecificationBuilderManager dynamicSpecificationBuilderManager
    ) {
        super(generalMapper, generalRepository, criteriaFieldResolver, dynamicSpecificationBuilderManager);
    }
}

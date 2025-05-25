package org.khasanof.core.service.query;

import org.khasanof.core.domain.common.DbTypes;
import org.khasanof.core.repository.base.IGeneralRepository;
import org.khasanof.core.service.criteria.DbTypesCriteria;
import org.khasanof.core.service.dto.DbTypesDTO;
import org.khasanof.core.service.mapper.base.IGeneralMapper;
import org.khasanof.core.service.query.specification.DynamicSpecificationQueryService;
import org.khasanof.core.service.query.specification.core.helper.CriteriaFieldResolver;
import org.khasanof.core.service.query.specification.core.manager.DynamicSpecificationBuilderManager;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for executing complex queries for {@link DbTypes} entities in the database.
 * The main input is a {@link DbTypesCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DbTypesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DbTypesQueryService extends DynamicSpecificationQueryService<DbTypes, DbTypesDTO, DbTypesCriteria> {

    public DbTypesQueryService(IGeneralMapper<DbTypes, DbTypesDTO> generalMapper,
                               IGeneralRepository<DbTypes> generalRepository,
                               CriteriaFieldResolver criteriaFieldResolver,
                               DynamicSpecificationBuilderManager dynamicSpecificationBuilderManager
    ) {
        super(generalMapper, generalRepository, criteriaFieldResolver, dynamicSpecificationBuilderManager);
    }
}

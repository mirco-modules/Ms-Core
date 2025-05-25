package org.khasanof.core.service.query;

import org.springframework.stereotype.Service;
import org.khasanof.core.domain.common.Numbers;
import org.khasanof.core.repository.base.IGeneralRepository;
import org.khasanof.core.service.criteria.NumbersCriteria;
import org.khasanof.core.service.dto.NumbersDTO;
import org.khasanof.core.service.mapper.base.IGeneralMapper;
import org.khasanof.core.service.query.specification.DynamicSpecificationQueryService;
import org.khasanof.core.service.query.specification.core.helper.CriteriaFieldResolver;
import org.khasanof.core.service.query.specification.core.manager.DynamicSpecificationBuilderManager;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.query
 * @since 12/11/2024 2:13 PM
 */
@Service
public class NumbersQueryService extends DynamicSpecificationQueryService<Numbers, NumbersDTO, NumbersCriteria> {

    public NumbersQueryService(IGeneralMapper<Numbers, NumbersDTO> generalMapper,
                               IGeneralRepository<Numbers> generalRepository,
                               CriteriaFieldResolver criteriaFieldResolver,
                               DynamicSpecificationBuilderManager dynamicSpecificationBuilderManager) {

        super(generalMapper, generalRepository, criteriaFieldResolver, dynamicSpecificationBuilderManager);
    }
}

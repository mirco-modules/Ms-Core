package org.khasanof.core.web.rest;

import io.swagger.v3.oas.annotations.Hidden;
import org.khasanof.core.domain.common.Variables;
import org.khasanof.core.service.base.IGeneralService;
import org.khasanof.core.service.criteria.VariablesCriteria;
import org.khasanof.core.service.dto.VariablesDTO;
import org.khasanof.core.service.query.base.IGeneralQueryService;
import org.khasanof.core.web.rest.base.GeneralQueryResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link Variables}.
 */
@Hidden
@RestController
@RequestMapping("/api/variables")
public class VariablesResource extends GeneralQueryResource<Variables, VariablesDTO, VariablesCriteria> {

    public VariablesResource(IGeneralService<Variables, VariablesDTO> generalService,
                             IGeneralQueryService<Variables, VariablesDTO, VariablesCriteria> generalQueryService
    ) {
        super(generalService, generalQueryService);
    }
}

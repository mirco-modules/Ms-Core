package org.khasanof.core.web.rest;

import io.swagger.v3.oas.annotations.Hidden;
import org.khasanof.core.domain.common.DbTypes;
import org.khasanof.core.service.base.IGeneralService;
import org.khasanof.core.service.criteria.DbTypesCriteria;
import org.khasanof.core.service.dto.DbTypesDTO;
import org.khasanof.core.service.query.base.IGeneralQueryService;
import org.khasanof.core.web.rest.base.GeneralQueryResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link DbTypes}.
 */
@Hidden
@RestController
@RequestMapping("/api/db-types")
public class DbTypesResource extends GeneralQueryResource<DbTypes, DbTypesDTO, DbTypesCriteria> {

    public DbTypesResource(IGeneralService<DbTypes, DbTypesDTO> generalService,
                           IGeneralQueryService<DbTypes, DbTypesDTO, DbTypesCriteria> generalQueryService
    ) {
        super(generalService, generalQueryService);
    }
}

package org.khasanof.core.service.impl;

import org.khasanof.core.domain.common.DbTypes;
import org.khasanof.core.repository.base.IGeneralRepository;
import org.khasanof.core.service.DbTypesService;
import org.khasanof.core.service.base.impl.GeneralValidateService;
import org.khasanof.core.service.dto.DbTypesDTO;
import org.khasanof.core.service.mapper.base.IGeneralMapper;
import org.khasanof.core.service.validator.manager.IGeneralValidatorManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DbTypes}.
 */
@Service
@Transactional
public class DbTypesServiceImpl extends GeneralValidateService<DbTypes, DbTypesDTO> implements DbTypesService {

    public DbTypesServiceImpl(IGeneralMapper<DbTypes, DbTypesDTO> generalMapper,
                              IGeneralRepository<DbTypes> generalRepository,
                              IGeneralValidatorManager generalValidatorManager
    ) {
        super(generalMapper, generalRepository, generalValidatorManager);
    }
}

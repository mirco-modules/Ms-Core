package org.khasanof.core.service.impl;

import org.khasanof.core.domain.common.Variables;
import org.khasanof.core.repository.base.IGeneralRepository;
import org.khasanof.core.service.base.impl.GeneralValidateService;
import org.khasanof.core.service.mapper.base.IGeneralMapper;
import org.khasanof.core.service.validator.manager.IGeneralValidatorManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.khasanof.core.repository.common.VariablesRepository;
import org.khasanof.core.service.VariablesService;
import org.khasanof.core.service.dto.VariablesDTO;
import org.khasanof.core.service.mapper.VariablesMapper;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Variables}.
 */
@Service
@Transactional
public class VariablesServiceImpl extends GeneralValidateService<Variables, VariablesDTO> implements VariablesService {

    public VariablesServiceImpl(IGeneralMapper<Variables, VariablesDTO> generalMapper,
                                IGeneralRepository<Variables> generalRepository,
                                IGeneralValidatorManager generalValidatorManager
    ) {
        super(generalMapper, generalRepository, generalValidatorManager);
    }
}

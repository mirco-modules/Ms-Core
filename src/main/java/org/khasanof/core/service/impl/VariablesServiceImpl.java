package org.khasanof.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.khasanof.core.domain.Variables;
import org.khasanof.core.repository.VariablesRepository;
import org.khasanof.core.service.VariablesService;
import org.khasanof.core.service.dto.VariablesDTO;
import org.khasanof.core.service.mapper.VariablesMapper;

import java.util.Optional;

/**
 * Service Implementation for managing {@link org.khasanof.core.domain.Variables}.
 */
@Service
@Transactional
public class VariablesServiceImpl implements VariablesService {

    private static final Logger LOG = LoggerFactory.getLogger(VariablesServiceImpl.class);

    private final VariablesRepository variablesRepository;

    private final VariablesMapper variablesMapper;

    public VariablesServiceImpl(VariablesRepository variablesRepository, VariablesMapper variablesMapper) {
        this.variablesRepository = variablesRepository;
        this.variablesMapper = variablesMapper;
    }

    @Override
    public VariablesDTO save(VariablesDTO variablesDTO) {
        LOG.debug("Request to save Variables : {}", variablesDTO);
        Variables variables = variablesMapper.toEntity(variablesDTO);
        variables = variablesRepository.save(variables);
        return variablesMapper.toDto(variables);
    }

    @Override
    public VariablesDTO update(VariablesDTO variablesDTO) {
        LOG.debug("Request to update Variables : {}", variablesDTO);
        Variables variables = variablesMapper.toEntity(variablesDTO);
        variables = variablesRepository.save(variables);
        return variablesMapper.toDto(variables);
    }

    @Override
    public Optional<VariablesDTO> partialUpdate(VariablesDTO variablesDTO) {
        LOG.debug("Request to partially update Variables : {}", variablesDTO);

        return variablesRepository
            .findById(variablesDTO.getId())
            .map(existingVariables -> {
                variablesMapper.partialUpdate(existingVariables, variablesDTO);

                return existingVariables;
            })
            .map(variablesRepository::save)
            .map(variablesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VariablesDTO> findOne(Long id) {
        LOG.debug("Request to get Variables : {}", id);
        return variablesRepository.findById(id).map(variablesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Variables : {}", id);
        variablesRepository.deleteById(id);
    }
}

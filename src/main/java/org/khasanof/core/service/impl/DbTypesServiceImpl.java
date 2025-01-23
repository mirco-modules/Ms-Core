package org.khasanof.core.service.impl;

import org.khasanof.core.domain.DbTypes;
import org.khasanof.core.repository.DbTypesRepository;
import org.khasanof.core.service.DbTypesService;
import org.khasanof.core.service.dto.DbTypesDTO;
import org.khasanof.core.service.mapper.DbTypesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link org.khasanof.core.domain.DbTypes}.
 */
@Service
@Transactional
public class DbTypesServiceImpl implements DbTypesService {

    private final Logger log = LoggerFactory.getLogger(DbTypesServiceImpl.class);

    private final DbTypesRepository dbTypesRepository;

    private final DbTypesMapper dbTypesMapper;

    public DbTypesServiceImpl(DbTypesRepository dbTypesRepository, DbTypesMapper dbTypesMapper) {
        this.dbTypesRepository = dbTypesRepository;
        this.dbTypesMapper = dbTypesMapper;
    }

    @Override
    public DbTypesDTO save(DbTypesDTO dbTypesDTO) {
        log.debug("Request to save DbTypes : {}", dbTypesDTO);
        DbTypes dbTypes = dbTypesMapper.toEntity(dbTypesDTO);
        dbTypes = dbTypesRepository.save(dbTypes);
        return dbTypesMapper.toDto(dbTypes);
    }

    @Override
    public DbTypesDTO update(DbTypesDTO dbTypesDTO) {
        log.debug("Request to update DbTypes : {}", dbTypesDTO);
        DbTypes dbTypes = dbTypesMapper.toEntity(dbTypesDTO);
        dbTypes = dbTypesRepository.save(dbTypes);
        return dbTypesMapper.toDto(dbTypes);
    }

    @Override
    public Optional<DbTypesDTO> partialUpdate(DbTypesDTO dbTypesDTO) {
        log.debug("Request to partially update DbTypes : {}", dbTypesDTO);

        return dbTypesRepository
            .findById(dbTypesDTO.getId())
            .map(existingDbTypes -> {
                dbTypesMapper.partialUpdate(existingDbTypes, dbTypesDTO);

                return existingDbTypes;
            })
            .map(dbTypesRepository::save)
            .map(dbTypesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DbTypesDTO> findOne(Long id) {
        log.debug("Request to get DbTypes : {}", id);
        return dbTypesRepository.findById(id).map(dbTypesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DbTypes : {}", id);
        dbTypesRepository.deleteById(id);
    }
}

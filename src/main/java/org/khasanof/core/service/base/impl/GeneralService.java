package org.khasanof.core.service.base.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.errors.DefaultErrorKeys;
import org.khasanof.core.errors.exception.BadRequestAlertException;
import org.khasanof.core.repository.base.IGeneralRepository;
import org.khasanof.core.service.base.IGeneralService;
import org.khasanof.core.service.dto.base.IDto;
import org.khasanof.core.service.mapper.base.IGeneralMapper;

import java.util.List;
import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.base.impl
 * @since 12/4/2024 6:16 PM
 */
@Slf4j
@Transactional
public abstract class GeneralService<E extends IEntity, D extends IDto> implements IGeneralService<E, D> {

    protected final IGeneralMapper<E, D> generalMapper;
    protected final IGeneralRepository<E> generalRepository;

    public GeneralService(IGeneralMapper<E, D> generalMapper, IGeneralRepository<E> generalRepository) {
        this.generalMapper = generalMapper;
        this.generalRepository = generalRepository;
    }

    /**
     * Get all entities
     *
     * @return the entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<D> getAll() {
        log.debug("Request to get all");
        List<E> entities = generalRepository.findAll();
        return generalMapper.toDto(entities);
    }

    /**
     * Get the "id" entity.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<D> getById(Long id) {
        log.debug("Request to get : {}", id);
        return generalRepository.findById(id)
                .map(generalMapper::toDto);
    }

    /**
     * Save or update a entity
     *
     * @param dto the entity to save.
     * @return the persisted entity.
     */
    @Override
    public D submit(D dto) {
        if (dto.getId() == null) {
            return save(dto);
        }
        return update(dto);
    }

    /**
     *
     * @param dto
     * @return
     */
    private D save(D dto) {
        log.debug("Request to save : {}", dto);
        preSave(dto);

        E entity = generalMapper.toEntity(dto);
        entity = generalRepository.save(entity);
        dto = generalMapper.toDto(entity);

        postSave(entity, dto);
        return dto;
    }

    protected void preSave(D dto) {
        if (dto.getId() != null) {
            throw new BadRequestAlertException(DefaultErrorKeys.ID_EXISTS);
        }
    }

    protected void postSave(E entity, D dto) {
    }

    /**
     *
     * @param dto
     * @return
     */
    private D update(D dto) {
        log.debug("Request to update : {}", dto);
        preUpdate(dto);

        E entity = generalMapper.toEntity(dto);
        entity = generalRepository.save(entity);
        dto = generalMapper.toDto(entity);

        postUpdate(entity, dto);
        return dto;
    }

    protected void preUpdate(D dto) {
        if (!generalRepository.existsById(dto.getId())) {
            throw new BadRequestAlertException(DefaultErrorKeys.NOT_FOUND);
        }
    }

    protected void postUpdate(E entity, D dto) {
    }

    /**
     * Delete the "id" entity.
     *
     * @param id the id of the entity.
     * @return delete result
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete : {}", id);
        preDelete(id);
        generalRepository.deleteById(id);
        postDelete(id);
        log.debug("Entity successfully deleted : {}", id);
    }

    /**
     *
     * @param id
     */
    protected void preDelete(Long id) {
    }

    /**
     *
     * @param id
     */
    protected void postDelete(Long id) {
    }
}

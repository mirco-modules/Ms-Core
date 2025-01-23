package org.khasanof.core.service.query.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;
import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.repository.base.IGeneralRepository;
import org.khasanof.core.service.criteria.base.ICriteria;
import org.khasanof.core.service.dto.base.IDto;
import org.khasanof.core.service.mapper.base.IGeneralMapper;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.query.base
 * @since 12/5/2024 10:51 AM
 */
@Slf4j
public abstract class GeneralQueryService<E extends IEntity, D extends IDto, C extends ICriteria> extends QueryService<E> implements IGeneralQueryService<E, D, C> {

    protected final IGeneralMapper<E, D> generalMapper;
    protected final IGeneralRepository<E> generalRepository;

    protected GeneralQueryService(IGeneralMapper<E, D> generalMapper, IGeneralRepository<E> generalRepository) {
        this.generalMapper = generalMapper;
        this.generalRepository = generalRepository;
    }

    /**
     *
     * @param criteria
     * @param page
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Page<D> findByCriteria(C criteria, Pageable page) {
        log.debug("find by criteria : {}, page : {}", criteria, page);
        Specification<E> specification = createSpecification(criteria);
        return generalRepository.findAll(specification, page)
                .map(generalMapper::toDto);
    }

    /**
     *
     * @param criteria
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public long countByCriteria(C criteria) {
        log.debug("count by criteria : {}", criteria);
        Specification<E> specification = createSpecification(criteria);
        return generalRepository.count(specification);
    }
}

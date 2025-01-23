package org.khasanof.core.web.rest.base;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.khasanof.core.service.criteria.base.ICriteria;
import org.khasanof.core.service.dto.base.IDto;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.core.web.rest.base
 * @since 12/5/2024 10:59 AM
 */
public interface IGeneralQueryResource<D extends IDto, C extends ICriteria> {

    /**
     *
     * @param criteria
     * @param pageable
     * @return
     */
    @GetMapping
    ResponseEntity<List<D>> getAll(C criteria, Pageable pageable);

    /**
     *
     * @param criteria
     * @return
     */
    @GetMapping("/count")
    ResponseEntity<Long> count(C criteria);
}

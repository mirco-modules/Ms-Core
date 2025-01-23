package org.khasanof.core.web.rest.base;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.khasanof.core.service.dto.base.IDto;

import java.net.URISyntaxException;

/**
 * @author Nurislom
 * @see org.khasanof.core.web.rest.base
 * @since 12/4/2024 6:42 PM
 */
public interface IGeneralResource<D extends IDto> {

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    ResponseEntity<D> getById(@PathVariable Long id);

    /**
     *
     * @param dto
     * @return
     */
    @PostMapping
    ResponseEntity<D> submit(@Valid @RequestBody D dto);

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> delete(@PathVariable Long id);
}

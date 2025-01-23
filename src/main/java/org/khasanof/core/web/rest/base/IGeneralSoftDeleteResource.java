package org.khasanof.core.web.rest.base;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Nurislom
 * @see org.khasanof.core.web.rest.base
 * @since 12/25/2024 3:42 PM
 */
public interface IGeneralSoftDeleteResource {

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/soft/{id}")
    ResponseEntity<Boolean> softDelete(@PathVariable Long id);
}

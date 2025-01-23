package org.khasanof.core.web.rest.base;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.khasanof.core.service.dto.HierarchicalDTO;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.core.web.rest.base
 * @since 1/22/2025 3:30 PM
 */
public interface IHierarchicalResource {

    /**
     *
     * @return
     */
    @GetMapping("/hierarchical")
    ResponseEntity<List<HierarchicalDTO>> getAllHierarchical();
}

package org.khasanof.core.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.web.util.ResponseUtil;
import org.khasanof.core.domain.common.Numbers;
import org.khasanof.core.service.NumbersService;
import org.khasanof.core.service.criteria.NumbersCriteria;
import org.khasanof.core.service.dto.NextNumberDTO;
import org.khasanof.core.service.dto.NumbersDTO;
import org.khasanof.core.service.query.base.IGeneralQueryService;
import org.khasanof.core.web.rest.base.GeneralQueryResource;

/**
 * @author Nurislom
 * @see org.khasanof.core.web.rest
 * @since 12/11/2024 2:14 PM
 */
@Slf4j
@RestController
@RequestMapping("/api/numbers")
public class NumbersResource extends GeneralQueryResource<Numbers, NumbersDTO, NumbersCriteria> {

    private final NumbersService numbersService;

    public NumbersResource(NumbersService generalService, IGeneralQueryService<Numbers, NumbersDTO, NumbersCriteria> generalQueryService) {
        super(generalService, generalQueryService);
        this.numbersService = generalService;
    }

    /**
     *
     * @param entityName
     * @return
     */
    @GetMapping("/next/{entityName}")
    public ResponseEntity<NextNumberDTO> getNextNumber(@PathVariable String entityName) {
        log.debug("REST request to get next number: {}", entityName);
        return ResponseUtil.wrapOrNotFound(numbersService.nextNumber(entityName));
    }
}

package org.khasanof.core.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;
import org.khasanof.core.repository.VariablesRepository;
import org.khasanof.core.service.query.VariablesQueryService;
import org.khasanof.core.service.VariablesService;
import org.khasanof.core.service.criteria.VariablesCriteria;
import org.khasanof.core.service.dto.VariablesDTO;
import org.khasanof.core.errors.exception.BadRequestAlertException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link org.khasanof.core.domain.Variables}.
 */
@RestController
@RequestMapping("/api/variables")
public class VariablesResource {

    private static final Logger LOG = LoggerFactory.getLogger(VariablesResource.class);

    private static final String ENTITY_NAME = "Variables";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VariablesService variablesService;

    private final VariablesRepository variablesRepository;

    private final VariablesQueryService variablesQueryService;

    public VariablesResource(
        VariablesService variablesService,
        VariablesRepository variablesRepository,
        VariablesQueryService variablesQueryService
    ) {
        this.variablesService = variablesService;
        this.variablesRepository = variablesRepository;
        this.variablesQueryService = variablesQueryService;
    }

    /**
     * {@code POST  /variables} : Create a new variables.
     *
     * @param variablesDTO the variablesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new variablesDTO, or with status {@code 400 (Bad Request)} if the variables has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<VariablesDTO> createVariables(@Valid @RequestBody VariablesDTO variablesDTO) throws URISyntaxException {
        LOG.debug("REST request to save Variables : {}", variablesDTO);
        if (variablesDTO.getId() != null) {
            throw new BadRequestAlertException("A new variables cannot already have an ID", ENTITY_NAME, "idexists");
        }
        variablesDTO = variablesService.save(variablesDTO);
        return ResponseEntity.created(new URI("/api/variables/" + variablesDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, variablesDTO.getId().toString()))
            .body(variablesDTO);
    }

    /**
     * {@code PUT  /variables/:id} : Updates an existing variables.
     *
     * @param id the id of the variablesDTO to save.
     * @param variablesDTO the variablesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated variablesDTO,
     * or with status {@code 400 (Bad Request)} if the variablesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the variablesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<VariablesDTO> updateVariables(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody VariablesDTO variablesDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update Variables : {}, {}", id, variablesDTO);
        if (variablesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, variablesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!variablesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        variablesDTO = variablesService.update(variablesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, variablesDTO.getId().toString()))
            .body(variablesDTO);
    }

    /**
     * {@code PATCH  /variables/:id} : Partial updates given fields of an existing variables, field will ignore if it is null
     *
     * @param id the id of the variablesDTO to save.
     * @param variablesDTO the variablesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated variablesDTO,
     * or with status {@code 400 (Bad Request)} if the variablesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the variablesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the variablesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<VariablesDTO> partialUpdateVariables(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody VariablesDTO variablesDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Variables partially : {}, {}", id, variablesDTO);
        if (variablesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, variablesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!variablesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<VariablesDTO> result = variablesService.partialUpdate(variablesDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, variablesDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /variables} : get all the variables.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of variables in body.
     */
    @GetMapping("")
    public ResponseEntity<List<VariablesDTO>> getAllVariables(
        VariablesCriteria criteria,
        Pageable pageable
    ) {
        LOG.debug("REST request to get Variables by criteria: {}", criteria);

        Page<VariablesDTO> page = variablesQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /variables/count} : count all the variables.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countVariables(VariablesCriteria criteria) {
        LOG.debug("REST request to count Variables by criteria: {}", criteria);
        return ResponseEntity.ok().body(variablesQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /variables/:id} : get the "id" variables.
     *
     * @param id the id of the variablesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the variablesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<VariablesDTO> getVariables(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Variables : {}", id);
        Optional<VariablesDTO> variablesDTO = variablesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(variablesDTO);
    }

    /**
     * {@code DELETE  /variables/:id} : delete the "id" variables.
     *
     * @param id the id of the variablesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVariables(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Variables : {}", id);
        variablesService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

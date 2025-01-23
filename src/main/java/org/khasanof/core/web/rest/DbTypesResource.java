package org.khasanof.core.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.khasanof.core.errors.DefaultErrorKeys;
import org.khasanof.core.errors.exception.BadRequestAlertException;
import org.khasanof.core.repository.DbTypesRepository;
import org.khasanof.core.service.query.DbTypesQueryService;
import org.khasanof.core.service.DbTypesService;
import org.khasanof.core.service.criteria.DbTypesCriteria;
import org.khasanof.core.service.dto.DbTypesDTO;
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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link org.khasanof.core.domain.DbTypes}.
 */
@RestController
@RequestMapping("/api/db-types")
public class DbTypesResource {

    private final Logger log = LoggerFactory.getLogger(DbTypesResource.class);

    private static final String ENTITY_NAME = "DbTypes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DbTypesService dbTypesService;

    private final DbTypesRepository dbTypesRepository;

    private final DbTypesQueryService dbTypesQueryService;

    public DbTypesResource(DbTypesService dbTypesService, DbTypesRepository dbTypesRepository, DbTypesQueryService dbTypesQueryService) {
        this.dbTypesService = dbTypesService;
        this.dbTypesRepository = dbTypesRepository;
        this.dbTypesQueryService = dbTypesQueryService;
    }

    /**
     * {@code POST  /db-types} : Create a new dbTypes.
     *
     * @param dbTypesDTO the dbTypesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dbTypesDTO, or with status {@code 400 (Bad Request)} if the dbTypes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DbTypesDTO> createDbTypes(@Valid @RequestBody DbTypesDTO dbTypesDTO) throws URISyntaxException {
        log.debug("REST request to save DbTypes : {}", dbTypesDTO);
        if (dbTypesDTO.getId() != null) {
            throw new BadRequestAlertException("A new dbTypes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (dbTypesRepository.existsByName(dbTypesDTO.getName())) {
            throw new BadRequestAlertException(DefaultErrorKeys.NAME_ALREADY_EXIST, ENTITY_NAME);
        }
        dbTypesDTO = dbTypesService.save(dbTypesDTO);
        return ResponseEntity.created(new URI("/api/db-types/" + dbTypesDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, dbTypesDTO.getId().toString()))
            .body(dbTypesDTO);
    }

    /**
     * {@code PUT  /db-types/:id} : Updates an existing dbTypes.
     *
     * @param id the id of the dbTypesDTO to save.
     * @param dbTypesDTO the dbTypesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dbTypesDTO,
     * or with status {@code 400 (Bad Request)} if the dbTypesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dbTypesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DbTypesDTO> updateDbTypes(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DbTypesDTO dbTypesDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DbTypes : {}, {}", id, dbTypesDTO);
        if (dbTypesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dbTypesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dbTypesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        dbTypesDTO = dbTypesService.update(dbTypesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, dbTypesDTO.getId().toString()))
            .body(dbTypesDTO);
    }

    /**
     * {@code PATCH  /db-types/:id} : Partial updates given fields of an existing dbTypes, field will ignore if it is null
     *
     * @param id the id of the dbTypesDTO to save.
     * @param dbTypesDTO the dbTypesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dbTypesDTO,
     * or with status {@code 400 (Bad Request)} if the dbTypesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the dbTypesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the dbTypesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DbTypesDTO> partialUpdateDbTypes(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DbTypesDTO dbTypesDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DbTypes partially : {}, {}", id, dbTypesDTO);
        if (dbTypesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dbTypesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dbTypesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DbTypesDTO> result = dbTypesService.partialUpdate(dbTypesDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, dbTypesDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /db-types} : get all the dbTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dbTypes in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DbTypesDTO>> getAllDbTypes(
        DbTypesCriteria criteria,
        Pageable pageable
    ) {
        log.debug("REST request to get DbTypes by criteria: {}", criteria);

        Page<DbTypesDTO> page = dbTypesQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /db-types/count} : count all the dbTypes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countDbTypes(DbTypesCriteria criteria) {
        log.debug("REST request to count DbTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(dbTypesQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /db-types/:id} : get the "id" dbTypes.
     *
     * @param id the id of the dbTypesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dbTypesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DbTypesDTO> getDbTypes(@PathVariable("id") Long id) {
        log.debug("REST request to get DbTypes : {}", id);
        Optional<DbTypesDTO> dbTypesDTO = dbTypesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dbTypesDTO);
    }

    /**
     * {@code DELETE  /db-types/:id} : delete the "id" dbTypes.
     *
     * @param id the id of the dbTypesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDbTypes(@PathVariable("id") Long id) {
        log.debug("REST request to delete DbTypes : {}", id);
        dbTypesService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

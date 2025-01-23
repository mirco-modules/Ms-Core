package org.khasanof.core.service.base.impl;

import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.domain.types.IHierarchical;
import org.khasanof.core.repository.base.IHierarchicalRepository;
import org.khasanof.core.service.base.IHierarchicalService;
import org.khasanof.core.service.dto.HierarchicalDTO;
import org.khasanof.core.service.dto.base.IDto;
import org.khasanof.core.service.mapper.HierarchicalChildrenMapper;
import org.khasanof.core.service.mapper.base.IGeneralMapper;
import org.khasanof.core.service.validator.manager.GeneralValidatorManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.base.impl
 * @since 1/22/2025 1:48 PM
 */
public abstract class HierarchicalService<E extends IEntity & IHierarchical<E>, D extends IDto> extends GeneralValidateService<E, D> implements IHierarchicalService<E, D> {

    protected final IHierarchicalRepository<E> hierarchicalRepository;
    protected final HierarchicalChildrenMapper<E> hierarchicalChildrenMapper;

    public HierarchicalService(IGeneralMapper<E, D> generalMapper, IHierarchicalRepository<E> hierarchicalRepository, GeneralValidatorManager generalValidatorManager) {
        super(generalMapper, hierarchicalRepository, generalValidatorManager);
        this.hierarchicalRepository = hierarchicalRepository;
        this.hierarchicalChildrenMapper = new HierarchicalChildrenMapper<>(hierarchicalRepository);
    }

    /**
     * @return
     */
    @Override
    public List<HierarchicalDTO> getAllByHierarchical() {
        List<E> entities = hierarchicalRepository.findByParentIsNull();
        return entitiesMapHierarchy(entities, hierarchicalChildrenMapper);
    }

    /**
     *
     * @param entities
     * @param childMapper
     * @return
     */
    protected List<HierarchicalDTO> entitiesMapHierarchy(List<E> entities, Function<E, List<E>> childMapper) {
        Map<Long, HierarchicalDTO> hierarchies = new HashMap<>();
        return entities.stream()
                .map(entity -> toHierarchyDTO(entity, childMapper, hierarchies))
                .collect(Collectors.toList());
    }

    /**
     *
     * @param entity
     * @param childMapper
     * @param dtoMap
     * @return
     */
    protected HierarchicalDTO toHierarchyDTO(E entity, Function<E, List<E>> childMapper, Map<Long, HierarchicalDTO> dtoMap) {
        if (entity == null) {
            return null;
        }

        Long entityId = entity.getId();

        if (dtoMap.containsKey(entityId)) {
            return dtoMap.get(entityId);
        }

        HierarchicalDTO dto = createHierarchyDTO(entity);

        dtoMap.put(entityId, dto);

        if (entity.getParent() != null) {
            HierarchicalDTO parent = createHierarchyDTO(entity.getParent());
            dto.setParent(parent);
        }

        List<E> children = childMapper.apply(entity);

        if (children != null && !children.isEmpty()) {
            List<HierarchicalDTO> childDTOs = children.stream()
                    .map(child -> toHierarchyDTO(child, childMapper, dtoMap))
                    .collect(Collectors.toList());
            dto.setChildren(childDTOs);
        }

        return dto;
    }

    /**
     *
     * @param entity
     * @return
     */
    private HierarchicalDTO createHierarchyDTO(E entity) {
        HierarchicalDTO hierarchicalDTO = new HierarchicalDTO();

        hierarchicalDTO.setId(entity.getId());
        hierarchicalDTO.setName(entity.getName());

        return hierarchicalDTO;
    }
}

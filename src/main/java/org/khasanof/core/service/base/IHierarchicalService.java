package org.khasanof.core.service.base;

import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.domain.types.IHierarchical;
import org.khasanof.core.service.dto.HierarchicalDTO;
import org.khasanof.core.service.dto.base.IDto;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.base
 * @since 1/22/2025 1:47 PM
 */
public interface IHierarchicalService<E extends IEntity & IHierarchical, D extends IDto> extends IGeneralService<E, D> {

    /**
     * Get all entities by hierarchy
     *
     * @return the entities
     */
    List<HierarchicalDTO> getAllByHierarchical();
}

package org.khasanof.core.service.mapper;

import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.domain.types.IHierarchical;
import org.khasanof.core.repository.base.IHierarchicalRepository;

import java.util.List;
import java.util.function.Function;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.mapper
 * @since 1/22/2025 2:27 PM
 */
public class HierarchicalChildrenMapper<E extends IEntity & IHierarchical<E>> implements Function<E, List<E>> {

    private final IHierarchicalRepository<E> hierarchicalRepository;

    public HierarchicalChildrenMapper(IHierarchicalRepository<E> hierarchicalRepository) {
        this.hierarchicalRepository = hierarchicalRepository;
    }

    /**
     *
     * @param entity the function argument
     * @return
     */
    @Override
    public List<E> apply(E entity) {
        return hierarchicalRepository.findChildrenByParentId(entity.getId());
    }
}

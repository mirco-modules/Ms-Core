package org.khasanof.core.service.processor.impl;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Component;
import org.khasanof.core.constants.DbTypesConstants;
import org.khasanof.core.domain.DbTypes;
import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.repository.DbTypesRepository;
import org.khasanof.core.service.IEntityPrefixResolver;
import org.khasanof.core.service.IEntitySynonymResolver;
import org.khasanof.core.service.scanner.DynamicClassScanningComponentProvider;

import java.util.Objects;

import static org.khasanof.core.util.ExtractClassNameUtil.extractClassName;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.processor.impl
 * @since 12/12/2024 10:57 AM
 */
@Component
public class IEntityDbTypesProcessor extends DefaultDbTypesProcessor {

    private final IEntityPrefixResolver iEntityPrefixResolver;
    private final IEntitySynonymResolver iEntitySynonymResolver;
    private final DynamicClassScanningComponentProvider dynamicClassScanningComponentProvider;

    public IEntityDbTypesProcessor(DbTypesRepository dbTypesRepository,
                                   IEntityPrefixResolver iEntityPrefixResolver,
                                   IEntitySynonymResolver iEntitySynonymResolver,
                                   DynamicClassScanningComponentProvider dynamicClassScanningComponentProvider) {
        super(dbTypesRepository);
        this.iEntityPrefixResolver = iEntityPrefixResolver;
        this.iEntitySynonymResolver = iEntitySynonymResolver;
        this.dynamicClassScanningComponentProvider = dynamicClassScanningComponentProvider;
    }

    /**
     *
     */
    @Override
    public void process() {
        var components = dynamicClassScanningComponentProvider.findComponents(new AssignableTypeFilter(IEntity.class));
        components.forEach(this::processEachComponent);
    }

    /**
     * @param beanDefinition
     */
    private void processEachComponent(BeanDefinition beanDefinition) {
        String beanClassName = beanDefinition.getBeanClassName();
        String extractClassName = extractClassName(beanClassName);

        String resolvedPrefix = resolveEntityPrefix(beanDefinition);
        String resolvedSynonym = iEntitySynonymResolver.resolve(beanDefinition);

        saveOrUpdate(resolvedPrefix, resolvedSynonym, extractClassName);
    }

    /**
     * @param beanDefinition
     * @return
     */
    private String resolveEntityPrefix(BeanDefinition beanDefinition) {
        String resolvePrefix = iEntityPrefixResolver.resolve(beanDefinition);
        return resolvePrefix == null ? DbTypesConstants.ENTITY_PREFIX : resolvePrefix;
    }

    /**
     * @param prefix
     * @param name
     */
    private void saveOrUpdate(String prefix, String synonym, String name) {
        dbTypesRepository.findByName(name)
                .ifPresentOrElse(dbTypes -> checkAndUpdatePrefix(dbTypes, prefix, synonym),
                        () -> persistDbTypes(prefix, name, synonym, false));
    }

    /**
     * @param dbTypes
     * @param prefix
     */
    private void checkAndUpdatePrefix(DbTypes dbTypes, String prefix, String synonym) {
        if (!Objects.equals(dbTypes.getPrefix(), prefix)) {
            dbTypes.setPrefix(prefix);
        }
        if (!Objects.equals(dbTypes.getSynonym(), synonym)) {
            dbTypes.setSynonym(synonym);
        }
        dbTypesRepository.save(dbTypes);
    }
}

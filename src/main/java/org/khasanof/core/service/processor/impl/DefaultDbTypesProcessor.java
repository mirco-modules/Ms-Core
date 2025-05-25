package org.khasanof.core.service.processor.impl;

import org.springframework.stereotype.Component;
import org.khasanof.core.constants.DbTypesConstants;
import org.khasanof.core.domain.common.DbTypes;
import org.khasanof.core.repository.common.DbTypesRepository;
import org.khasanof.core.service.processor.RootProcessor;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.processor.impl
 * @since 11/20/2024 2:50 PM
 */
@Component
public class DefaultDbTypesProcessor implements RootProcessor {

    protected final DbTypesRepository dbTypesRepository;

    public DefaultDbTypesProcessor(DbTypesRepository dbTypesRepository) {
        this.dbTypesRepository = dbTypesRepository;
    }

    /**
     *
     */
    @Override
    public void process() {
        persistDbTypes(DbTypesConstants.TYPE_PREFIX, "boolean", true);
        persistDbTypes(DbTypesConstants.TYPE_PREFIX, "byte", true);
        persistDbTypes(DbTypesConstants.TYPE_PREFIX, "short", true);
        persistDbTypes(DbTypesConstants.TYPE_PREFIX, "int", true);
        persistDbTypes(DbTypesConstants.TYPE_PREFIX, "long", true);
        persistDbTypes(DbTypesConstants.TYPE_PREFIX, "float", true);
        persistDbTypes(DbTypesConstants.TYPE_PREFIX, "double", true);
        persistDbTypes(DbTypesConstants.TYPE_PREFIX, "String", true);
    }

    /**
     * @param prefix
     * @param name
     * @param isPrimitive
     * @return
     */
    protected void persistDbTypes(String prefix, String name, Boolean isPrimitive) {
        persistDbTypes(prefix, name, name, isPrimitive);
    }

    /**
     * @param prefix
     * @param synonym
     * @param name
     * @param isPrimitive
     */
    protected void persistDbTypes(String prefix, String name, String synonym, Boolean isPrimitive) {
        if (dbTypesRepository.existsByName(name)) {
            return;
        }
        var dbTypes = createDbTypes(prefix, name, synonym, isPrimitive);
        dbTypesRepository.save(dbTypes);
    }

    /**
     * @param prefix
     * @param name
     * @param synonym
     * @param isPrimitive
     * @return
     */
    protected DbTypes createDbTypes(String prefix, String name, String synonym, Boolean isPrimitive) {
        DbTypes dbTypes = new DbTypes();

        dbTypes.setName(name);
        dbTypes.setPrefix(prefix);
        dbTypes.setSynonym(synonym);
        dbTypes.setIsPrimitive(isPrimitive);

        return dbTypes;
    }
}

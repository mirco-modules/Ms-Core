package org.khasanof.core.service.processor.impl;

import org.khasanof.core.constants.NumberProcessorConstants;
import org.khasanof.core.domain.DbTypes;
import org.khasanof.core.domain.Numbers;
import org.khasanof.core.domain.types.ICodeable;
import org.khasanof.core.repository.DbTypesRepository;
import org.khasanof.core.repository.NumbersRepository;
import org.khasanof.core.service.HQLExecutorService;
import org.khasanof.core.service.processor.RootProcessor;
import org.khasanof.core.service.scanner.DynamicClassScanningComponentProvider;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.khasanof.core.constants.NumberProcessorConstants.*;
import static org.khasanof.core.util.ExtractClassNameUtil.extractClassName;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.processor.impl
 * @since 12/11/2024 2:21 PM
 */
@Component
public class DefaultNumbersProcessor implements RootProcessor {

    private static final Long DEFAULT_NEXT_NUMBER = 1L;

    private final NumbersRepository numbersRepository;
    private final DbTypesRepository dbTypesRepository;
    private final HQLExecutorService hqlExecutorService;
    private final DynamicClassScanningComponentProvider dynamicClassScanningComponentProvider;

    public DefaultNumbersProcessor(NumbersRepository numbersRepository,
                                   DbTypesRepository dbTypesRepository,
                                   HQLExecutorService hqlExecutorService,
                                   DynamicClassScanningComponentProvider dynamicClassScanningComponentProvider) {

        this.numbersRepository = numbersRepository;
        this.dbTypesRepository = dbTypesRepository;
        this.hqlExecutorService = hqlExecutorService;
        this.dynamicClassScanningComponentProvider = dynamicClassScanningComponentProvider;
    }

    /**
     *
     */
    @Override
    public void process() {
        var components = dynamicClassScanningComponentProvider.findComponents(new AssignableTypeFilter(ICodeable.class));
        components.forEach(this::processEachComponent);
    }

    /**
     *
     * @param beanDefinition
     */
    private void processEachComponent(BeanDefinition beanDefinition) {
        String beanClassName = beanDefinition.getBeanClassName();
        String extractedClassName = extractClassName(beanClassName);

        Optional<DbTypes> optionalDbType = dbTypesRepository.findByName(extractedClassName);
        optionalDbType.ifPresent(this::persistDbTypeNumber);
    }

    /**
     *
     * @param dbType
     */
    private void persistDbTypeNumber(DbTypes dbType) {
        Long nextNumber = getNextNumber(dbType);
        if (!numbersRepository.existsByDbType(dbType)) {
            Numbers numbers = createNUmber(dbType, nextNumber);
            numbersRepository.save(numbers);
        }
    }

    /**
     *
     * @param dbType
     * @return
     */
    private Long getNextNumber(DbTypes dbType) {
        Long count = hqlExecutorService.executeQuery(GET_ENTITY_COUNT, dbType.getName());
        return count <= 0 ? DEFAULT_NEXT_NUMBER : getEntityMaxCode(dbType);
    }

    /**
     *
     * @param dbType
     * @return
     */
    private Long getEntityMaxCode(DbTypes dbType) {
        Integer maxCode = hqlExecutorService.executeQuery(GET_MAX_CODE_QUERY, CODE_FIELD, dbType.getName());
        return maxCode.longValue() + 1;
    }

    /**
     *
     * @param dbTypes
     * @return
     */
    private Numbers createNUmber(DbTypes dbTypes, Long nextNumber) {
        Numbers numbers = new Numbers();
        numbers.setNextNumber(nextNumber);
        numbers.setDbType(dbTypes);
        return numbers;
    }

    /**
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 10;
    }
}

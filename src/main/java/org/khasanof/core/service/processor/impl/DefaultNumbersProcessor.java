package org.khasanof.core.service.processor.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Component;
import org.khasanof.core.constants.NumberProcessorConstants;
import org.khasanof.core.domain.DbTypes;
import org.khasanof.core.domain.Numbers;
import org.khasanof.core.domain.types.ICodeable;
import org.khasanof.core.repository.DbTypesRepository;
import org.khasanof.core.repository.NumbersRepository;
import org.khasanof.core.service.processor.RootProcessor;
import org.khasanof.core.service.scanner.DynamicClassScanningComponentProvider;

import java.util.Optional;

import static org.khasanof.core.constants.NumberProcessorConstants.CODE_FIELD;
import static org.khasanof.core.util.ExtractClassNameUtil.extractClassName;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.processor.impl
 * @since 12/11/2024 2:21 PM
 */
@Component
@SuppressWarnings("unchecked")
public class DefaultNumbersProcessor implements RootProcessor {

    private static final Long DEFAULT_NEXT_NUMBER = 1L;

    private final EntityManager em;
    private final NumbersRepository numbersRepository;
    private final DbTypesRepository dbTypesRepository;
    private final DynamicClassScanningComponentProvider dynamicClassScanningComponentProvider;

    public DefaultNumbersProcessor(EntityManager em,
                                   NumbersRepository numbersRepository,
                                   DbTypesRepository dbTypesRepository,
                                   DynamicClassScanningComponentProvider dynamicClassScanningComponentProvider) {

        this.em = em;
        this.numbersRepository = numbersRepository;
        this.dbTypesRepository = dbTypesRepository;
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
        Long count = executeQuery(NumberProcessorConstants.GET_ENTITY_COUNT, dbType.getName());
        return count <= 0 ? DEFAULT_NEXT_NUMBER : getEntityMaxCode(dbType);
    }

    /**
     *
     * @param dbType
     * @return
     */
    private Long getEntityMaxCode(DbTypes dbType) {
        Integer maxCode = executeQuery(NumberProcessorConstants.GET_MAX_CODE_QUERY, CODE_FIELD, dbType.getName());
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
     * @param hql
     * @param params
     * @return
     */
    private <T> T executeQuery(String hql, Object... params) {
        String formattedHql = String.format(hql, params);
        Query query = em.createQuery(formattedHql);
        return (T) query.getSingleResult();
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

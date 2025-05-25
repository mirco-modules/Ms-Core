package org.khasanof.core.service.processor.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.khasanof.core.config.RootProperties;
import org.khasanof.core.domain.common.Variables;
import org.khasanof.core.repository.common.VariablesRepository;
import org.khasanof.core.service.data.AbstractDataProcessor;
import org.khasanof.core.service.mapper.VariablesMapper;
import org.khasanof.core.service.processor.RootProcessor;

import java.util.Comparator;

import static java.lang.Boolean.parseBoolean;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.processor.manager
 * @since 11/20/2024 2:48 PM
 */
@Slf4j
@Component
public class RootProcessorManager implements InitializingBean {

    private final RootProperties rootProperties;
    private final VariablesMapper variablesMapper;
    private final ApplicationContext applicationContext;
    private final VariablesRepository variablesRepository;

    public RootProcessorManager(
            RootProperties rootProperties,
            VariablesMapper variablesMapper,
            ApplicationContext applicationContext,
            VariablesRepository variablesRepository
    ) {
        this.rootProperties = rootProperties;
        this.variablesMapper = variablesMapper;
        this.applicationContext = applicationContext;
        this.variablesRepository = variablesRepository;
    }


    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        if (!rootProperties.getEnabledProcessors()) {
            log.warn("Root Processors disabled !!!");
            return;
        }
        processorsExecutor();
    }

    /**
     *
     */
    private void processorsExecutor() {
        applicationContext.getBeansOfType(RootProcessor.class)
                .values()
                .stream()
                .sorted(Comparator.comparing(RootProcessor::getOrder))
                .forEach(this::executeProcessor);
    }

    /**
     *
     * @param processor
     */
    private void executeProcessor(RootProcessor processor) {
        if (processor instanceof AbstractDataProcessor) {
            if (!isExecuted(processor)) {
                processor.process();
                saveOrUpdateVariable(processor);
            }
            return;
        }
        processor.process();
    }

    /**
     *
     * @param processor
     */
    private void saveOrUpdateVariable(RootProcessor processor) {
        var variables = processorToVariable(processor);
        variablesRepository.save(variables);
    }

    /**
     *
     * @param processor
     * @return
     */
    private Variables processorToVariable(RootProcessor processor) {
        return variablesRepository.findByName(getSimpleName(processor))
                .map(variables -> variables.value(Boolean.TRUE.toString()))
                .orElse(variablesMapper.toEntity(processor));
    }

    /**
     * @param processor
     * @return
     */
    private boolean isExecuted(RootProcessor processor) {
        return variablesRepository.findByName(getSimpleName(processor))
                .map(variable -> parseBoolean(variable.getValue()))
                .orElse(false);
    }

    /**
     * @param processor
     * @return
     */
    private String getSimpleName(RootProcessor processor) {
        return processor.getClass().getSimpleName();
    }
}

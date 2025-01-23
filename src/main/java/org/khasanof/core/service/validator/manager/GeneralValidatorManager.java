package org.khasanof.core.service.validator.manager;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.khasanof.core.service.dto.base.IDto;
import org.khasanof.core.service.validator.IGeneralValidator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.validator.manager
 * @since 12/19/2024 2:31 PM
 */
@Service
@SuppressWarnings({"rawtypes", "unchecked"})
public class GeneralValidatorManager implements IGeneralValidatorManager, InitializingBean {

    private final Set<IGeneralValidator> generalValidators = new HashSet<>();

    private final ApplicationContext applicationContext;

    public GeneralValidatorManager(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     *
     * @param dto
     */
    @Override
    public void preSave(IDto dto) {
        consumerValidate(generalValidator -> generalValidator.preSave(dto), dto);
    }

    /**
     *
     * @param dto
     */
    @Override
    public void preUpdate(IDto dto) {
        consumerValidate(generalValidator -> generalValidator.preUpdate(dto), dto);
    }

    /**
     *
     * @param id
     */
    @Override
    public void preDelete(Long id) {
    }

    /**
     *
     * @param validatorConsumer
     * @param dto
     */
    private void consumerValidate(Consumer<IGeneralValidator> validatorConsumer, IDto dto) {
        this.generalValidators.stream()
                .filter(generalUpdateValidator -> {
                    return generalUpdateValidator.support(dto);
                })
                .forEach(validatorConsumer);
    }

    /**
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        generalValidators.addAll(getGeneralValidators());
    }

    /**
     *
     * @return
     */
    private Collection<IGeneralValidator> getGeneralValidators() {
        return applicationContext.getBeansOfType(IGeneralValidator.class)
                .values();
    }
}

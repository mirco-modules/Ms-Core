package org.khasanof.core.service.validator;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.service.dto.base.IDto;

import java.lang.reflect.ParameterizedType;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.validator
 * @since 12/19/2024 12:34 PM
 */
@Slf4j
@SuppressWarnings({"unchecked"})
public abstract class AbstractGeneralValidator<E extends IEntity, D extends IDto> implements IGeneralValidator<D> {

    protected final Class<E> persistenceClass;
    protected final Class<D> persistenceDtoClass;

    public AbstractGeneralValidator() {
        this.persistenceClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.persistenceDtoClass = (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    /**
     *
     * @param dto
     * @return
     */
    @Override
    public boolean support(D dto) {
        return Objects.equals(persistenceDtoClass, dto.getClass()) || persistenceDtoClass.isAssignableFrom(dto.getClass());
    }
}

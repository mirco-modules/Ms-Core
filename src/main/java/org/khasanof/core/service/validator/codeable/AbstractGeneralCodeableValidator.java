package org.khasanof.core.service.validator.codeable;

import org.khasanof.core.domain.types.ICodeable;
import org.khasanof.core.domain.types.IEntity;
import org.khasanof.core.errors.DefaultErrorKeys;
import org.khasanof.core.errors.exception.BadRequestAlertException;
import org.khasanof.core.repository.base.IGeneralCodeableRepository;
import org.khasanof.core.service.dto.base.IDto;
import org.khasanof.core.service.validator.AbstractGeneralValidator;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.validator.codeable
 * @since 12/19/2024 3:20 PM
 */
public abstract class AbstractGeneralCodeableValidator<E extends IEntity & ICodeable, D extends IDto> extends AbstractGeneralValidator<E, D> {

    protected final IGeneralCodeableRepository<E> generalCodeableRepository;

    public AbstractGeneralCodeableValidator(IGeneralCodeableRepository<E> generalCodeableRepository) {
        this.generalCodeableRepository = generalCodeableRepository;
    }

    /**
     *
     * @param dto
     */
    @Override
    public void preSave(D dto) {
        if (dto instanceof ICodeable codeable) {
            checkExistCode(codeable);
        }
    }

    /**
     *
     * @param dto
     */
    @Override
    public void preUpdate(D dto) {
        if (dto instanceof ICodeable codeableDto) {
            preUpdateInternal(dto, codeableDto);
        }
    }

    /**
     *
     * @param dto
     * @param codeableDto
     */
    private void preUpdateInternal(D dto, ICodeable codeableDto) {
        Optional<E> optionalEntity = generalCodeableRepository.findById(dto.getId());
        E entity = optionalEntity.orElseThrow(() -> new BadRequestAlertException(DefaultErrorKeys.NOT_FOUND));

        if (!Objects.equals(entity.getCode(), codeableDto.getCode())) {
            checkExistCode(codeableDto);
        }
    }

    /**
     *
     * @param dto
     */
    @Override
    public void preDelete(D dto) {
    }

    /**
     *
     * @param codeable
     */
    private void checkExistCode(ICodeable codeable) {
        if (generalCodeableRepository.existByCode(codeable.getCode())) {
            throw new BadRequestAlertException(DefaultErrorKeys.CODE_ALREADY_EXIST);
        }
    }

    /**
     *
     * @param dto
     * @return
     */
    @Override
    public boolean support(D dto) {
        return super.support(dto) && dto instanceof ICodeable;
    }
}

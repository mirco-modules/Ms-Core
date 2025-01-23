package org.khasanof.core.service;

import org.khasanof.core.domain.Numbers;
import org.khasanof.core.service.base.IGeneralService;
import org.khasanof.core.service.dto.NextNumberDTO;
import org.khasanof.core.service.dto.NumbersDTO;

import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.core.service
 * @since 12/11/2024 2:02 PM
 */
public interface NumbersService extends IGeneralService<Numbers, NumbersDTO> {

    /**
     *
     * @param entityName
     * @return
     */
    Optional<NextNumberDTO> nextNumber(String entityName);
}

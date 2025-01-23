package org.khasanof.core.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.khasanof.core.domain.Numbers;
import org.khasanof.core.repository.NumbersRepository;
import org.khasanof.core.repository.base.IGeneralRepository;
import org.khasanof.core.service.NumbersService;
import org.khasanof.core.service.base.impl.GeneralService;
import org.khasanof.core.service.dto.NextNumberDTO;
import org.khasanof.core.service.dto.NumbersDTO;
import org.khasanof.core.service.mapper.NumbersMapper;
import org.khasanof.core.service.mapper.base.IGeneralMapper;

import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.impl
 * @since 12/11/2024 2:11 PM
 */
@Service
public class NumbersServiceImpl extends GeneralService<Numbers, NumbersDTO> implements NumbersService {

    private final NumbersRepository numbersRepository;

    public NumbersServiceImpl(NumbersMapper generalMapper, NumbersRepository generalRepository) {
        super(generalMapper, generalRepository);
        this.numbersRepository = generalRepository;
    }

    /**
     *
     * @param entityName
     * @return
     */
    @Override
    @Transactional
    public Optional<NextNumberDTO> nextNumber(String entityName) {
        return numbersRepository.findByDbType_Name(entityName)
                .map(this::getNextNumber);
    }

    /**
     *
     * @param number
     * @return
     */
    private NextNumberDTO getNextNumber(Numbers number) {
        NextNumberDTO nextNumber = new NextNumberDTO(number.getNextNumber());
        incrementDbTypeNumber(number);
        return nextNumber;
    }

    /**
     *
     * @param number
     */
    private void incrementDbTypeNumber(Numbers number) {
        number.setNextNumber(number.getNextNumber() + 1);
        numbersRepository.save(number);
    }
}

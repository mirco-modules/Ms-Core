package org.khasanof.core.service.mapper;

import org.mapstruct.Mapper;
import org.khasanof.core.domain.Numbers;
import org.khasanof.core.service.dto.NumbersDTO;
import org.khasanof.core.service.mapper.base.IGeneralMapper;

/**
 * @author Nurislom
 * @see org.khasanof.core.service.mapper
 * @since 12/11/2024 2:12 PM
 */
@Mapper(componentModel = "spring")
public interface NumbersMapper extends IGeneralMapper<Numbers, NumbersDTO> {
}

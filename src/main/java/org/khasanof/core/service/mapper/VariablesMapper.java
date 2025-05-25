package org.khasanof.core.service.mapper;

import org.khasanof.core.domain.common.Variables;
import org.khasanof.core.service.dto.VariablesDTO;
import org.khasanof.core.service.mapper.base.IGeneralMapper;
import org.khasanof.core.service.processor.RootProcessor;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Variables} and its DTO {@link VariablesDTO}.
 */
@Mapper(componentModel = "spring")
public interface VariablesMapper extends IGeneralMapper<Variables, VariablesDTO> {

    /**
     *
     * @param rootProcessor
     * @return
     */
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "name", expression = "java(rootProcessor.getClass().getSimpleName())")
    @Mapping(target = "value", constant = "true")
    Variables toEntity(RootProcessor rootProcessor);
}

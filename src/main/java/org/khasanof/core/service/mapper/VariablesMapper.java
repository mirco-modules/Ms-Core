package org.khasanof.core.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.khasanof.core.domain.Variables;
import org.khasanof.core.service.dto.VariablesDTO;
import org.khasanof.core.service.processor.RootProcessor;

/**
 * Mapper for the entity {@link Variables} and its DTO {@link VariablesDTO}.
 */
@Mapper(componentModel = "spring")
public interface VariablesMapper extends EntityMapper<VariablesDTO, Variables> {

    /**
     *
     * @param rootProcessor
     * @return
     */
    @Mapping(target = "name", expression = "java(rootProcessor.getClass().getSimpleName())")
    @Mapping(target = "value", constant = "true")
    Variables toEntity(RootProcessor rootProcessor);
}

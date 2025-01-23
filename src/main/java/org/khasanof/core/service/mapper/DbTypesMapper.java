package org.khasanof.core.service.mapper;

import org.khasanof.core.domain.DbTypes;
import org.khasanof.core.service.dto.DbTypesDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link DbTypes} and its DTO {@link DbTypesDTO}.
 */
@Mapper(componentModel = "spring")
public interface DbTypesMapper extends EntityMapper<DbTypesDTO, DbTypes> {}

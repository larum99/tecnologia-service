package com.onclass.tecnologia.infrastructure.entrypoints.mapper;

import com.onclass.tecnologia.domain.model.Tecnologia;
import com.onclass.tecnologia.infrastructure.entrypoints.dto.TecnologiaDTO;
import com.onclass.tecnologia.infrastructure.entrypoints.util.MapperConstants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = MapperConstants.COMPONENT_MODEL_SPRING)
public interface TecnologiaMapper {

    @Mapping(target = MapperConstants.FIELD_ID, ignore = true)
    @Mapping(source = MapperConstants.FIELD_NOMBRE, target = MapperConstants.FIELD_NOMBRE)
    @Mapping(source = MapperConstants.FIELD_DESCRIPCION, target = MapperConstants.FIELD_DESCRIPCION)
    Tecnologia toModel(TecnologiaDTO dto);

    TecnologiaDTO toDTO(Tecnologia model);
}

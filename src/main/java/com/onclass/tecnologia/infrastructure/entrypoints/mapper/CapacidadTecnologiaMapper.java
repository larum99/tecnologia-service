package com.onclass.tecnologia.infrastructure.entrypoints.mapper;

import com.onclass.tecnologia.domain.model.CapacidadTecnologia;
import com.onclass.tecnologia.infrastructure.entrypoints.dto.CapacidadTecnologiaDTO;
import com.onclass.tecnologia.infrastructure.entrypoints.util.MapperConstants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = MapperConstants.COMPONENT_MODEL_SPRING)
public interface CapacidadTecnologiaMapper {

    @Mapping(target = MapperConstants.FIELD_ID, ignore = true) 
    CapacidadTecnologia toModel(CapacidadTecnologiaDTO dto);

    CapacidadTecnologiaDTO toDTO(CapacidadTecnologia model);
}

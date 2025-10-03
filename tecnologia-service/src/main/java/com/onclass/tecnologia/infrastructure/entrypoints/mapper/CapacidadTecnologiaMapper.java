package com.onclass.tecnologia.infrastructure.entrypoints.mapper;

import com.onclass.tecnologia.domain.model.CapacidadTecnologia;
import com.onclass.tecnologia.infrastructure.entrypoints.dto.CapacidadTecnologiaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CapacidadTecnologiaMapper {

    @Mapping(target = "id", ignore = true) 
    CapacidadTecnologia toModel(CapacidadTecnologiaDTO dto);

    CapacidadTecnologiaDTO toDTO(CapacidadTecnologia model);
}

package com.onclass.tecnologia.infrastructure.entrypoints.mapper;

import com.onclass.tecnologia.domain.model.Tecnologia;
import com.onclass.tecnologia.infrastructure.entrypoints.dto.TecnologiaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TecnologiaMapper {

    @Mapping(target = "id", ignore = true) // el id lo maneja la BD
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "descripcion", target = "descripcion")
    Tecnologia toModel(TecnologiaDTO dto);

    TecnologiaDTO toDTO(Tecnologia model);
}

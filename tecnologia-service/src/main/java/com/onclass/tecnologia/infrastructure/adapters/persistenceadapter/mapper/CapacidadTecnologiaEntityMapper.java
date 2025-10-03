package com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.mapper;

import com.onclass.tecnologia.domain.model.CapacidadTecnologia;
import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.entity.CapacidadTecnologiaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CapacidadTecnologiaEntityMapper {
    @Mapping(source = "capacidadId", target = "capacidadId")
    @Mapping(source = "tecnologiaId", target = "tecnologiaId")
    CapacidadTecnologia toModel(CapacidadTecnologiaEntity entity);

    @Mapping(source = "capacidadId", target = "capacidadId")
    @Mapping(source = "tecnologiaId", target = "tecnologiaId")
    CapacidadTecnologiaEntity toEntity(CapacidadTecnologia model);
}


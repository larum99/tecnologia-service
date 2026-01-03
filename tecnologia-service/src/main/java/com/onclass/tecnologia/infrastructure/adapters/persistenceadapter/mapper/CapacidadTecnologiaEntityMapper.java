package com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.mapper;

import com.onclass.tecnologia.domain.model.CapacidadTecnologia;
import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.entity.CapacidadTecnologiaEntity;
import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.util.MapperConstants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = MapperConstants.COMPONENT_MODEL_SPRING)
public interface CapacidadTecnologiaEntityMapper {
    @Mapping(source = MapperConstants.FIELD_CAPACIDAD_ID, target = MapperConstants.FIELD_CAPACIDAD_ID)
    @Mapping(source = MapperConstants.FIELD_TECNOLOGIA_ID, target = MapperConstants.FIELD_TECNOLOGIA_ID)
    CapacidadTecnologia toModel(CapacidadTecnologiaEntity entity);

    @Mapping(source = MapperConstants.FIELD_CAPACIDAD_ID, target = MapperConstants.FIELD_CAPACIDAD_ID)
    @Mapping(source = MapperConstants.FIELD_TECNOLOGIA_ID, target = MapperConstants.FIELD_TECNOLOGIA_ID)
    CapacidadTecnologiaEntity toEntity(CapacidadTecnologia model);
}


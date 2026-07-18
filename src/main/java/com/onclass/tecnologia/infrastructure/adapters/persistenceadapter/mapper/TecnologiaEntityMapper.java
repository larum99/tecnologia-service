package com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.mapper;

import com.onclass.tecnologia.domain.model.Tecnologia;
import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.entity.TecnologiaEntity;
import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.util.MapperConstants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = MapperConstants.COMPONENT_MODEL_SPRING)
public interface TecnologiaEntityMapper {
    @Mapping(source = MapperConstants.FIELD_ID, target = MapperConstants.FIELD_ID)
    @Mapping(source = MapperConstants.FIELD_NOMBRE, target = MapperConstants.FIELD_NOMBRE)
    @Mapping(source = MapperConstants.FIELD_DESCRIPCION, target = MapperConstants.FIELD_DESCRIPCION)
    Tecnologia toModel(TecnologiaEntity entity);

    TecnologiaEntity toEntity(Tecnologia tecnologia);
}

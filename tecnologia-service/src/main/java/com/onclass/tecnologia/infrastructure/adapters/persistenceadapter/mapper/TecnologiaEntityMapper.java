package com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.mapper;

import com.onclass.tecnologia.domain.model.Tecnologia;
import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.entity.TecnologiaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TecnologiaEntityMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "descripcion", target = "descripcion")
    Tecnologia toModel(TecnologiaEntity entity);

    TecnologiaEntity toEntity(Tecnologia tecnologia);
}

package com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.util;

public final class MapperConstants {
    
    // Component model
    public static final String COMPONENT_MODEL_SPRING = "spring";
    
    // Field mappings - Tecnologia
    public static final String FIELD_ID = "id";
    public static final String FIELD_NOMBRE = "nombre";
    public static final String FIELD_DESCRIPCION = "descripcion";
    
    // Field mappings - CapacidadTecnologia
    public static final String FIELD_CAPACIDAD_ID = "capacidadId";
    public static final String FIELD_TECNOLOGIA_ID = "tecnologiaId";
    
    private MapperConstants() {
        throw new UnsupportedOperationException("Esta es una clase de constantes");
    }
}
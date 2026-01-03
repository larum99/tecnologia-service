package com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.util;

public final class EntityConstants {
    
    // Table names
    public static final String TABLE_TECNOLOGIAS = "tecnologias";
    public static final String TABLE_CAPACIDAD_TECNOLOGIAS = "capacidad_tecnologias";
    
    // Column names - CapacidadTecnologia
    public static final String COLUMN_ID_CAPACIDAD = "id_capacidad";
    public static final String COLUMN_ID_TECNOLOGIA = "id_tecnologia";
    
    private EntityConstants() {
        throw new UnsupportedOperationException("Esta es una clase de constantes");
    }
}
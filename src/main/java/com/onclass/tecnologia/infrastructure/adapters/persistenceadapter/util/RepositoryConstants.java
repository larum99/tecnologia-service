package com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.util;

public final class RepositoryConstants {

    // SQL Queries
    public static final String QUERY_FIND_TECNOLOGIAS_BY_CAPACIDAD_ID = """
        SELECT t.id, t.nombre, t.descripcion
        FROM capacidad_tecnologias ct
        JOIN tecnologias t ON ct.id_tecnologia = t.id
        WHERE ct.id_capacidad = :capacidadId
    """;
    
    public static final String QUERY_DELETE_BY_CAPACIDAD_IDS = "DELETE FROM capacidad_tecnologias WHERE id_capacidad IN (:capacidadIds)";
    
    public static final String QUERY_FIND_TECNOLOGIA_IDS_BY_CAPACIDADES = """
        SELECT DISTINCT id_tecnologia
        FROM capacidad_tecnologias
        WHERE id_capacidad IN (:capacidadIds)
    """;
    
    public static final String QUERY_COUNT_CAPACIDADES_BY_TECNOLOGIA_ID = """
        SELECT COUNT(*) 
        FROM capacidad_tecnologias 
        WHERE id_tecnologia = :tecnologiaId
    """;

    private RepositoryConstants() {
        throw new UnsupportedOperationException("Esta es una clase de constantes");
    }
}
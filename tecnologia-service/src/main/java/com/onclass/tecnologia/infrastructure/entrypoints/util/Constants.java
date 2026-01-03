package com.onclass.tecnologia.infrastructure.entrypoints.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    public static final String X_MESSAGE_ID = "x-message-id";
    public static final String TECNOLOGIA_ERROR = "Error en Tecnología - [ERROR]";
    public static final String TECNOLOGIA_PATH = "/tecnologias";
    public static final String CAPACIDAD_TECNOLOGIA_PATH = "/capacidad-tecnologias";
    
    // Route path segments
    public static final String PATH_CAPACIDAD_TECNOLOGIAS = "/{capacidadId}/tecnologias";
    public static final String PATH_BY_CAPACIDADES = "/by-capacidades";
    public static final String PATH_TECNOLOGIAS_BY_CAPACIDADES = "/tecnologias/by-capacidades";
    public static final String PATH_ID = "/{id}";
    public static final String PATH_COUNT_BY_TECNOLOGIA = "/count/by-tecnologia/{tecnologiaId}";
}

package com.onclass.tecnologia.infrastructure.entrypoints.util;

public final class HandlerConstants {
    
    // HTTP Status Messages
    public static final String STATUS_CREATED = "Creado exitosamente";
    public static final String STATUS_DELETED = "Eliminado exitosamente";
    public static final String STATUS_NOT_FOUND = "No encontrado";
    public static final String STATUS_BAD_REQUEST = "Solicitud inválida";
    public static final String STATUS_INTERNAL_ERROR = "Error interno del servidor";
    
    // Content Types
    public static final String CONTENT_TYPE_JSON = "application/json";
    
    // Header Names
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_X_MESSAGE_ID = "x-message-id";
    
    // Error Messages
    public static final String ERROR_INVALID_INPUT = "Datos de entrada inválidos";
    public static final String ERROR_PROCESSING_REQUEST = "Error procesando la solicitud";
    public static final String TECNOLOGIA_ERROR = "Error en Tecnología - [ERROR]";
    
    // Path Variables
    public static final String PATH_VARIABLE_ID = "id";
    
    // Log Messages
    public static final String LOG_TECNOLOGIA_CREATED = "Tecnología creada con messageId: {}";
    public static final String LOG_ERROR_DELETE_TECNOLOGIA = "Error al eliminar tecnología {}";
    
    private HandlerConstants() {
        throw new UnsupportedOperationException("Esta es una clase de constantes");
    }
}
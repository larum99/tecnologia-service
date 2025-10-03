package com.onclass.tecnologia.application.configSwagger;

public final class ApiConstants {
    private ApiConstants() {}

    // Rutas
    public static final String PATH_TECNOLOGIAS = "/tecnologias";
    public static final String PATH_CAPACIDAD_TECNOLOGIA = "/capacidad-tecnologias";

    // Headers
    public static final String HEADER_X_MESSAGE_ID = "x-message-id";
    public static final String HEADER_X_MESSAGE_ID_DESC = "Identificador único del mensaje";

    // Operation
    public static final String CREATE_TECNOLOGIA_OPERATION_ID = "createTecnologia";
    public static final String CREATE_TECNOLOGIA_SUMMARY = "Crear una nueva tecnología";
    public static final String REQUEST_BODY_DESCRIPTION = "Datos de la nueva tecnología";
    public static final String CREATE_CAPACIDAD_TECNOLOGIA_OPERATION_ID = "createCapacidadTecnologia";
    public static final String CREATE_CAPACIDAD_TECNOLOGIA_SUMMARY = "Crear nuevas capacidades para tecnologías";

    // Respuestas
    public static final String RESPONSE_201 = "Tecnología creada exitosamente";
    public static final String RESPONSE_400 = "Parámetros inválidos";
    public static final String RESPONSE_500 = "Error interno";
    public static final String RESPONSE_CAPACIDAD_TECNOLOGIA_201 = "Capacidades creadas exitosamente";
    public static final String RESPONSE_CAPACIDAD_TECNOLOGIA_400 = "Parámetros inválidos";
    public static final String RESPONSE_CAPACIDAD_TECNOLOGIA_500 = "Error interno";

    // Códigos HTTP
    public static final String HTTP_CREATED = "201";
    public static final String HTTP_BAD_REQUEST = "400";
    public static final String HTTP_INTERNAL_ERROR = "500";

    // Request body
    public static final String REQUEST_BODY_CAPACIDAD_TECNOLOGIA_DESCRIPTION = "Datos de la capacidad tecnológica";
}

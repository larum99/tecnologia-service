package com.onclass.tecnologia.application.configSwagger;

public final class ApiConstants {
    private ApiConstants() {}

    // Rutas
    public static final String PATH_TECNOLOGIAS = "/tecnologia-service/tecnologias";
    public static final String PATH_CAPACIDAD_TECNOLOGIA = "/tecnologia-service/capacidad-tecnologias";

    // Headers
    public static final String HEADER_X_MESSAGE_ID = "x-message-id";
    public static final String HEADER_X_MESSAGE_ID_DESC = "Identificador único del mensaje";

    // Operation
    public static final String CREATE_TECNOLOGIA_OPERATION_ID = "createTecnologia";
    public static final String CREATE_TECNOLOGIA_SUMMARY = "Crear una nueva tecnología";
    public static final String REQUEST_BODY_DESCRIPTION = "Datos de la nueva tecnología";
    public static final String CREATE_CAPACIDAD_TECNOLOGIA_OPERATION_ID = "createCapacidadTecnologia";
    public static final String CREATE_CAPACIDAD_TECNOLOGIA_SUMMARY = "Crear nuevas capacidades para tecnologías";
    public static final String GET_CAPACIDAD_TECNOLOGIA_OPERATION_ID = "getTecnologias";
    public static final String GET_CAPACIDAD_TECNOLOGIA_SUMMARY = "Obtener el listado de tecnologias por capacidad";
    public static final String DELETE_TECNOLOGIA_OPERATION_ID = "deleteTecnologia";
    public static final String DELETE_TECNOLOGIA_SUMMARY = "Eliminar una tecnología por ID";

    // Respuestas
    public static final String RESPONSE_201 = "Tecnología creada exitosamente";
    public static final String RESPONSE_204 = "Tecnología eliminada exitosamente.";
    public static final String RESPONSE_400 = "Parámetros inválidos";
    public static final String RESPONSE_404 = "No se encontró la tecnología especificada.";
    public static final String RESPONSE_500 = "Error interno";
    public static final String RESPONSE_CAPACIDAD_TECNOLOGIA_201 = "Capacidades creadas exitosamente";
    public static final String RESPONSE_CAPACIDAD_TECNOLOGIA_400 = "Parámetros inválidos";
    public static final String RESPONSE_CAPACIDAD_TECNOLOGIA_500 = "Error interno";

    // Códigos HTTP
    public static final String HTTP_CREATED = "201";
    public static final String HTTP_BAD_REQUEST = "400";
    public static final String HTTP_INTERNAL_ERROR = "500";
    public static final String HTTP_OK = "200";
    public static final String HTTP_NO_CONTENT = "204";
    public static final String HTTP_NOT_FOUND = "404";

    // Request body
    public static final String REQUEST_BODY_CAPACIDAD_TECNOLOGIA_DESCRIPTION = "Datos de la capacidad tecnológica";

    // Bean methods
    public static final String BEAN_METHOD_CREATE_CAPACIDAD_TECNOLOGIAS = "createCapacidadTecnologias";

    // Schema types
    public static final String SCHEMA_TYPE_STRING = "string";

    // Example names
    public static final String EXAMPLE_NAME_CAPACIDAD_TECNOLOGIA = "Ejemplo CapacidadTecnologia";

    // Paths adicionales
    public static final String PATH_COUNT_BY_TECNOLOGIA = "/count/by-tecnologia/{tecnologiaId}";

    // Operation IDs adicionales
    public static final String COUNT_CAPACIDADES_BY_TECNOLOGIA_OPERATION_ID = "countCapacidadesByTecnologiaId";

    // Summaries adicionales
    public static final String COUNT_CAPACIDADES_BY_TECNOLOGIA_SUMMARY = "Contar capacidades asociadas a una tecnología";

    // Descriptions adicionales
    public static final String COUNT_CAPACIDADES_BY_TECNOLOGIA_DESCRIPTION = "Obtiene el número de capacidades relacionadas con una tecnología específica.";

    // Bean methods adicionales
    public static final String BEAN_METHOD_COUNT_CAPACIDADES_BY_TECNOLOGIA = "countCapacidadesByTecnologiaId";

    // Parameter names
    public static final String PARAM_TECNOLOGIA_ID = "tecnologiaId";

    // Parameter descriptions
    public static final String PARAM_TECNOLOGIA_ID_DESC = "ID de la tecnología";

    // Response descriptions adicionales
    public static final String RESPONSE_COUNT_200 = "Conteo devuelto correctamente.";
    public static final String RESPONSE_COUNT_400 = "Solicitud inválida.";
    public static final String RESPONSE_COUNT_500 = "Error interno del servidor.";

    // Paths adicionales para delete
    public static final String PATH_BY_CAPACIDADES = "/by-capacidades";

    // Operation IDs para delete
    public static final String DELETE_TECNOLOGIAS_BY_CAPACIDADES_OPERATION_ID = "deleteTecnologiasByCapacidades";

    // Summaries para delete
    public static final String DELETE_TECNOLOGIAS_BY_CAPACIDADES_SUMMARY = "Eliminar tecnologías asociadas a una lista de capacidades";

    // Descriptions para delete
    public static final String DELETE_TECNOLOGIAS_BY_CAPACIDADES_DESCRIPTION = "Elimina todas las relaciones capacidad-tecnología asociadas a los IDs de capacidades proporcionados.";

    // Bean methods para delete
    public static final String BEAN_METHOD_DELETE_TECNOLOGIAS_BY_CAPACIDADES = "deleteTecnologiasByCapacidades";

    // Request body descriptions para delete
    public static final String REQUEST_BODY_DELETE_CAPACIDADES_DESCRIPTION = "Lista de IDs de capacidades cuyas relaciones con tecnologías deben eliminarse.";

    // Example names para delete
    public static final String EXAMPLE_NAME_IDS_CAPACIDADES = "Ejemplo IDs capacidades";

    // Example values para delete
    public static final String EXAMPLE_VALUE_IDS_CAPACIDADES = "[1, 2, 3]";

    // Response descriptions para delete
    public static final String RESPONSE_DELETE_204 = "Relaciones eliminadas correctamente.";
    public static final String RESPONSE_DELETE_400 = "Solicitud inválida.";
    public static final String RESPONSE_DELETE_500 = "Error interno del servidor.";

    // Paths adicionales para find
    public static final String PATH_TECNOLOGIAS_BY_CAPACIDADES = "/tecnologias/by-capacidades";

    // Operation IDs para find
    public static final String FIND_TECNOLOGIAS_IDS_BY_CAPACIDADES_OPERATION_ID = "findTecnologiasIdsByCapacidades";

    // Summaries para find
    public static final String FIND_TECNOLOGIAS_IDS_BY_CAPACIDADES_SUMMARY = "Obtener IDs de tecnologías por capacidades";

    // Descriptions para find
    public static final String FIND_TECNOLOGIAS_IDS_BY_CAPACIDADES_DESCRIPTION = "Retorna los IDs de tecnologías asociados a una lista de capacidades.";

    // Bean methods para find
    public static final String BEAN_METHOD_FIND_TECNOLOGIAS_IDS_BY_CAPACIDADES = "findTecnologiasIdsByCapacidades";

    // Request body descriptions para find
    public static final String REQUEST_BODY_FIND_IDS_CAPACIDADES_DESCRIPTION = "Lista de IDs de capacidades.";

    // Response descriptions para find
    public static final String RESPONSE_FIND_200 = "Lista de IDs de tecnologías retornada correctamente.";
    public static final String RESPONSE_FIND_400 = "Solicitud inválida.";
    public static final String RESPONSE_FIND_500 = "Error interno del servidor.";

    // Paths adicionales para get
    public static final String PATH_CAPACIDAD_TECNOLOGIAS = "/{capacidadId}/tecnologias";

    // Descriptions para get
    public static final String GET_CAPACIDAD_TECNOLOGIA_DESCRIPTION = "Obtiene todas las tecnologías asociadas a una capacidad específica.";

    // Bean methods para get
    public static final String BEAN_METHOD_LIST_TECNOLOGIAS_BY_CAPACIDAD = "listTecnologiasByCapacidad";

    // Parameter names para get
    public static final String PARAM_CAPACIDAD_ID = "capacidadId";

    // Parameter descriptions para get
    public static final String PARAM_CAPACIDAD_ID_DESC = "ID de la capacidad";

    // Response descriptions para get
    public static final String RESPONSE_GET_200 = "Listado de tecnologías devuelto correctamente";
    public static final String RESPONSE_GET_400 = "Parámetros inválidos";
    public static final String RESPONSE_GET_500 = "Error interno del servidor";

    // Bean methods para tecnologia
    public static final String BEAN_METHOD_CREATE_TECNOLOGIA = "createTecnologia";

    // Example names para tecnologia
    public static final String EXAMPLE_NAME_TECNOLOGIA = "Ejemplo Tecnologia";

    // Paths adicionales para tecnologia delete
    public static final String PATH_TECNOLOGIA_ID = "/{id}";

    // Descriptions para tecnologia delete
    public static final String DELETE_TECNOLOGIA_DESCRIPTION = "Elimina una tecnología existente por su ID.";

    // Bean methods para tecnologia delete
    public static final String BEAN_METHOD_DELETE_TECNOLOGIA = "deleteTecnologia";

    // Parameter names para tecnologia delete
    public static final String PARAM_ID = "id";

    // Parameter descriptions para tecnologia delete
    public static final String PARAM_ID_DESC = "ID de la tecnología a eliminar";
}

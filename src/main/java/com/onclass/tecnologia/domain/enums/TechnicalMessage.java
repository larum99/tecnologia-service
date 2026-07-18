package com.onclass.tecnologia.domain.enums;

public enum TechnicalMessage {

    INTERNAL_ERROR("500","Ha ocurrido un error interno, por favor intente nuevamente", ""),
    INVALID_REQUEST("400", "Solicitud incorrecta, por favor verifique los datos", ""),
    INVALID_PARAMETERS("400-1", "Parámetros inválidos, por favor verifique los datos", ""),

    // Mensajes específicos de Tecnología
    TECNOLOGIA_ALREADY_EXISTS("400-2","La tecnología ya está registrada","nombre"),
    TECNOLOGIA_NOMBRE_REQUIRED("400-3","El nombre de la tecnología es obligatorio","nombre"),
    TECNOLOGIA_DESCRIPCION_REQUIRED("400-4","La descripción de la tecnología es obligatoria","descripcion"),
    TECNOLOGIA_NOMBRE_TOO_LONG("400-5","El nombre de la tecnología supera los 50 caracteres","nombre"),
    TECNOLOGIA_DESCRIPCION_TOO_LONG("400-6","La descripción de la tecnología supera los 90 caracteres","descripcion"),

    // Nuevos mensajes para eliminación
    TECNOLOGIA_ID_INVALID("400-7", "El ID de la tecnología es inválido", "id"),
    TECNOLOGIA_NOT_FOUND("404-1", "La tecnología no existe", "id"),

    TECNOLOGIA_CREATED("201", "Tecnología creada exitosamente", "");

    private final String code;
    private final String description;
    private final String param;

    TechnicalMessage(String code, String description, String param) {
        this.code = code;
        this.description = description;
        this.param = param;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getParam() {
        return param;
    }
}

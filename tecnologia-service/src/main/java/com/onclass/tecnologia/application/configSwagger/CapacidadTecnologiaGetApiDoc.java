package com.onclass.tecnologia.application.configSwagger;

import com.onclass.tecnologia.infrastructure.entrypoints.handler.CapacidadTecnologiaHandlerImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@RouterOperation(
        path = ApiConstants.PATH_CAPACIDAD_TECNOLOGIA + "/{capacidadId}/tecnologias",
        beanClass = CapacidadTecnologiaHandlerImpl.class,
        beanMethod = "listTecnologiasByCapacidad",
        operation = @Operation(
                operationId = ApiConstants.GET_CAPACIDAD_TECNOLOGIA_OPERATION_ID,
                summary = ApiConstants.GET_CAPACIDAD_TECNOLOGIA_SUMMARY,
                description = "Obtiene todas las tecnologías asociadas a una capacidad específica.",
                parameters = {
                        @Parameter(
                                name = ApiConstants.HEADER_X_MESSAGE_ID,
                                in = ParameterIn.HEADER,
                                description = ApiConstants.HEADER_X_MESSAGE_ID_DESC,
                                required = true
                        ),
                        @Parameter(
                                name = "capacidadId",
                                in = ParameterIn.PATH,
                                description = "ID de la capacidad",
                                required = true
                        )
                },
                responses = {
                        @ApiResponse(responseCode = ApiConstants.HTTP_OK, description = "Listado de tecnologías devuelto correctamente"),
                        @ApiResponse(responseCode = ApiConstants.HTTP_BAD_REQUEST, description = "Parámetros inválidos"),
                        @ApiResponse(responseCode = ApiConstants.HTTP_INTERNAL_ERROR, description = "Error interno del servidor")
                }
        )
)
public @interface CapacidadTecnologiaGetApiDoc {}

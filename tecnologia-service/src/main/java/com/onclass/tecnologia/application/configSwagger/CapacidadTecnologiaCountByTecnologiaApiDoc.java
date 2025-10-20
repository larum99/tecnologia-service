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
        path = ApiConstants.PATH_CAPACIDAD_TECNOLOGIA + "/count/by-tecnologia/{tecnologiaId}",
        beanClass = CapacidadTecnologiaHandlerImpl.class,
        beanMethod = "countCapacidadesByTecnologiaId",
        operation = @Operation(
                operationId = "countCapacidadesByTecnologiaId",
                summary = "Contar capacidades asociadas a una tecnología",
                description = "Obtiene el número de capacidades relacionadas con una tecnología específica.",
                parameters = {
                        @Parameter(
                                name = ApiConstants.HEADER_X_MESSAGE_ID,
                                in = ParameterIn.HEADER,
                                required = true,
                                description = ApiConstants.HEADER_X_MESSAGE_ID_DESC
                        ),
                        @Parameter(
                                name = "tecnologiaId",
                                in = ParameterIn.PATH,
                                required = true,
                                description = "ID de la tecnología"
                        )
                },
                responses = {
                        @ApiResponse(responseCode = ApiConstants.HTTP_OK, description = "Conteo devuelto correctamente."),
                        @ApiResponse(responseCode = ApiConstants.HTTP_BAD_REQUEST, description = "Solicitud inválida."),
                        @ApiResponse(responseCode = ApiConstants.HTTP_INTERNAL_ERROR, description = "Error interno del servidor.")
                }
        )
)
public @interface CapacidadTecnologiaCountByTecnologiaApiDoc {}

package com.onclass.tecnologia.application.configSwagger;

import com.onclass.tecnologia.infrastructure.entrypoints.handler.CapacidadTecnologiaHandlerImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@RouterOperation(
        path = ApiConstants.PATH_CAPACIDAD_TECNOLOGIA + "/tecnologias/by-capacidades",
        beanClass = CapacidadTecnologiaHandlerImpl.class,
        beanMethod = "findTecnologiasIdsByCapacidades",
        operation = @Operation(
                operationId = "findTecnologiasIdsByCapacidades",
                summary = "Obtener IDs de tecnologías por capacidades",
                description = "Retorna los IDs de tecnologías asociados a una lista de capacidades.",
                parameters = {
                        @Parameter(
                                name = ApiConstants.HEADER_X_MESSAGE_ID,
                                in = ParameterIn.HEADER,
                                required = true,
                                description = ApiConstants.HEADER_X_MESSAGE_ID_DESC
                        )
                },
                requestBody = @RequestBody(
                        description = "Lista de IDs de capacidades.",
                        required = true,
                        content = @Content(
                                schema = @Schema(implementation = Long.class),
                                examples = {
                                        @ExampleObject(
                                                name = "Ejemplo IDs capacidades",
                                                value = "[1, 2, 3]"
                                        )
                                }
                        )
                ),
                responses = {
                        @ApiResponse(responseCode = ApiConstants.HTTP_OK, description = "Lista de IDs de tecnologías retornada correctamente."),
                        @ApiResponse(responseCode = ApiConstants.HTTP_BAD_REQUEST, description = "Solicitud inválida."),
                        @ApiResponse(responseCode = ApiConstants.HTTP_INTERNAL_ERROR, description = "Error interno del servidor.")
                }
        )
)
public @interface CapacidadTecnologiaFindIdsApiDoc {}

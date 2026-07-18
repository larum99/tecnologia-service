package com.onclass.tecnologia.application.configSwagger;

import com.onclass.tecnologia.infrastructure.entrypoints.handler.CapacidadTecnologiaHandlerImpl;
import com.onclass.tecnologia.infrastructure.entrypoints.dto.CapacidadTecnologiaDTO;
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
        path = ApiConstants.PATH_CAPACIDAD_TECNOLOGIA,
        beanClass = CapacidadTecnologiaHandlerImpl.class,
        beanMethod = ApiConstants.BEAN_METHOD_CREATE_CAPACIDAD_TECNOLOGIAS,
        operation = @Operation(
                operationId = ApiConstants.CREATE_CAPACIDAD_TECNOLOGIA_OPERATION_ID,
                summary = ApiConstants.CREATE_CAPACIDAD_TECNOLOGIA_SUMMARY,
                parameters = {
                        @Parameter(
                                name = ApiConstants.HEADER_X_MESSAGE_ID,
                                in = ParameterIn.HEADER,
                                description = ApiConstants.HEADER_X_MESSAGE_ID_DESC,
                                required = true,
                                schema = @Schema(type = ApiConstants.SCHEMA_TYPE_STRING)
                        )
                },
                requestBody = @RequestBody(
                        description = ApiConstants.REQUEST_BODY_CAPACIDAD_TECNOLOGIA_DESCRIPTION,
                        required = true,
                        content = @Content(
                                schema = @Schema(implementation = CapacidadTecnologiaDTO.class),
                                examples = {
                                        @ExampleObject(
                                                name = ApiConstants.EXAMPLE_NAME_CAPACIDAD_TECNOLOGIA,
                                                value = ApiExamples.CAPACIDAD_TECNOLOGIA_DTO_JSON
                                        )
                                }
                        )
                ),
                responses = {
                        @ApiResponse(responseCode = ApiConstants.HTTP_CREATED, description = ApiConstants.RESPONSE_CAPACIDAD_TECNOLOGIA_201),
                        @ApiResponse(responseCode = ApiConstants.HTTP_BAD_REQUEST, description = ApiConstants.RESPONSE_CAPACIDAD_TECNOLOGIA_400),
                        @ApiResponse(responseCode = ApiConstants.HTTP_INTERNAL_ERROR, description = ApiConstants.RESPONSE_CAPACIDAD_TECNOLOGIA_500)
                }
        )
)
public @interface CapacidadTecnologiaApiDoc {}

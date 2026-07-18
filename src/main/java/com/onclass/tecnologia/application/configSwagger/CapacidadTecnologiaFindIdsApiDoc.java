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
        path = ApiConstants.PATH_CAPACIDAD_TECNOLOGIA + ApiConstants.PATH_TECNOLOGIAS_BY_CAPACIDADES,
        beanClass = CapacidadTecnologiaHandlerImpl.class,
        beanMethod = ApiConstants.BEAN_METHOD_FIND_TECNOLOGIAS_IDS_BY_CAPACIDADES,
        operation = @Operation(
                operationId = ApiConstants.FIND_TECNOLOGIAS_IDS_BY_CAPACIDADES_OPERATION_ID,
                summary = ApiConstants.FIND_TECNOLOGIAS_IDS_BY_CAPACIDADES_SUMMARY,
                description = ApiConstants.FIND_TECNOLOGIAS_IDS_BY_CAPACIDADES_DESCRIPTION,
                parameters = {
                        @Parameter(
                                name = ApiConstants.HEADER_X_MESSAGE_ID,
                                in = ParameterIn.HEADER,
                                required = true,
                                description = ApiConstants.HEADER_X_MESSAGE_ID_DESC
                        )
                },
                requestBody = @RequestBody(
                        description = ApiConstants.REQUEST_BODY_FIND_IDS_CAPACIDADES_DESCRIPTION,
                        required = true,
                        content = @Content(
                                schema = @Schema(implementation = Long.class),
                                examples = {
                                        @ExampleObject(
                                                name = ApiConstants.EXAMPLE_NAME_IDS_CAPACIDADES,
                                                value = ApiExamples.IDS_CAPACIDADES_JSON
                                        )
                                }
                        )
                ),
                responses = {
                        @ApiResponse(responseCode = ApiConstants.HTTP_OK, description = ApiConstants.RESPONSE_FIND_200),
                        @ApiResponse(responseCode = ApiConstants.HTTP_BAD_REQUEST, description = ApiConstants.RESPONSE_FIND_400),
                        @ApiResponse(responseCode = ApiConstants.HTTP_INTERNAL_ERROR, description = ApiConstants.RESPONSE_FIND_500)
                }
        )
)
public @interface CapacidadTecnologiaFindIdsApiDoc {}

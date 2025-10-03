package com.onclass.tecnologia.application.configSwagger;

import com.onclass.tecnologia.infrastructure.entrypoints.dto.TecnologiaDTO;
import com.onclass.tecnologia.infrastructure.entrypoints.handler.TecnologiaHandlerImpl;
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
        path = ApiConstants.PATH_TECNOLOGIAS,
        beanClass = TecnologiaHandlerImpl.class,
        beanMethod = "createTecnologia",
        operation = @Operation(
                operationId = ApiConstants.CREATE_TECNOLOGIA_OPERATION_ID,
                summary = ApiConstants.CREATE_TECNOLOGIA_SUMMARY,
                parameters = {
                        @Parameter(
                                name = ApiConstants.HEADER_X_MESSAGE_ID,
                                in = ParameterIn.HEADER,
                                description = ApiConstants.HEADER_X_MESSAGE_ID_DESC,
                                required = true,
                                schema = @Schema(type = "string")
                        )
                },
                requestBody = @RequestBody(
                        description = ApiConstants.REQUEST_BODY_DESCRIPTION,
                        required = true,
                        content = @Content(
                                schema = @Schema(implementation = TecnologiaDTO.class),
                                examples = {
                                        @ExampleObject(
                                                name = "Ejemplo Tecnologia",
                                                value = ApiExamples.TECNOLOGIA_DTO_JSON
                                        )
                                }
                        )
                ),
                responses = {
                        @ApiResponse(responseCode = ApiConstants.HTTP_CREATED, description = ApiConstants.RESPONSE_201),
                        @ApiResponse(responseCode = ApiConstants.HTTP_BAD_REQUEST, description = ApiConstants.RESPONSE_400),
                        @ApiResponse(responseCode = ApiConstants.HTTP_INTERNAL_ERROR, description = ApiConstants.RESPONSE_500)
                }
        )
)
public @interface TecnologiaApiDoc {}

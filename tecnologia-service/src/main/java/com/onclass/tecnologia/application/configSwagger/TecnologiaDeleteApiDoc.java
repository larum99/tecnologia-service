package com.onclass.tecnologia.application.configSwagger;

import com.onclass.tecnologia.infrastructure.entrypoints.handler.TecnologiaHandlerImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@RouterOperation(
        path = ApiConstants.PATH_TECNOLOGIAS + "/{id}",
        beanClass = TecnologiaHandlerImpl.class,
        beanMethod = "deleteTecnologia",
        operation = @Operation(
                operationId = ApiConstants.DELETE_TECNOLOGIA_OPERATION_ID,
                summary = ApiConstants.DELETE_TECNOLOGIA_SUMMARY,
                description = "Elimina una tecnología existente por su ID.",
                parameters = {
                        @Parameter(
                                name = ApiConstants.HEADER_X_MESSAGE_ID,
                                in = ParameterIn.HEADER,
                                required = true,
                                description = ApiConstants.HEADER_X_MESSAGE_ID_DESC
                        ),
                        @Parameter(
                                name = "id",
                                in = ParameterIn.PATH,
                                required = true,
                                description = "ID de la tecnología a eliminar"
                        )
                },
                responses = {
                        @ApiResponse(responseCode = ApiConstants.HTTP_NO_CONTENT, description = ApiConstants.RESPONSE_204),
                        @ApiResponse(responseCode = ApiConstants.HTTP_NOT_FOUND, description = ApiConstants.RESPONSE_404),
                        @ApiResponse(responseCode = ApiConstants.HTTP_INTERNAL_ERROR, description = ApiConstants.RESPONSE_500)
                }
        )
)
public @interface TecnologiaDeleteApiDoc {}

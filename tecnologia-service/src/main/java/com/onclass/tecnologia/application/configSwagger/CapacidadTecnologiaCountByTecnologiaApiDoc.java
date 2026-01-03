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
        path = ApiConstants.PATH_CAPACIDAD_TECNOLOGIA + ApiConstants.PATH_COUNT_BY_TECNOLOGIA,
        beanClass = CapacidadTecnologiaHandlerImpl.class,
        beanMethod = ApiConstants.BEAN_METHOD_COUNT_CAPACIDADES_BY_TECNOLOGIA,
        operation = @Operation(
                operationId = ApiConstants.COUNT_CAPACIDADES_BY_TECNOLOGIA_OPERATION_ID,
                summary = ApiConstants.COUNT_CAPACIDADES_BY_TECNOLOGIA_SUMMARY,
                description = ApiConstants.COUNT_CAPACIDADES_BY_TECNOLOGIA_DESCRIPTION,
                parameters = {
                        @Parameter(
                                name = ApiConstants.HEADER_X_MESSAGE_ID,
                                in = ParameterIn.HEADER,
                                required = true,
                                description = ApiConstants.HEADER_X_MESSAGE_ID_DESC
                        ),
                        @Parameter(
                                name = ApiConstants.PARAM_TECNOLOGIA_ID,
                                in = ParameterIn.PATH,
                                required = true,
                                description = ApiConstants.PARAM_TECNOLOGIA_ID_DESC
                        )
                },
                responses = {
                        @ApiResponse(responseCode = ApiConstants.HTTP_OK, description = ApiConstants.RESPONSE_COUNT_200),
                        @ApiResponse(responseCode = ApiConstants.HTTP_BAD_REQUEST, description = ApiConstants.RESPONSE_COUNT_400),
                        @ApiResponse(responseCode = ApiConstants.HTTP_INTERNAL_ERROR, description = ApiConstants.RESPONSE_COUNT_500)
                }
        )
)
public @interface CapacidadTecnologiaCountByTecnologiaApiDoc {}

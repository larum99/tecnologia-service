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
        path = ApiConstants.PATH_CAPACIDAD_TECNOLOGIA + ApiConstants.PATH_CAPACIDAD_TECNOLOGIAS,
        beanClass = CapacidadTecnologiaHandlerImpl.class,
        beanMethod = ApiConstants.BEAN_METHOD_LIST_TECNOLOGIAS_BY_CAPACIDAD,
        operation = @Operation(
                operationId = ApiConstants.GET_CAPACIDAD_TECNOLOGIA_OPERATION_ID,
                summary = ApiConstants.GET_CAPACIDAD_TECNOLOGIA_SUMMARY,
                description = ApiConstants.GET_CAPACIDAD_TECNOLOGIA_DESCRIPTION,
                parameters = {
                        @Parameter(
                                name = ApiConstants.HEADER_X_MESSAGE_ID,
                                in = ParameterIn.HEADER,
                                description = ApiConstants.HEADER_X_MESSAGE_ID_DESC,
                                required = true
                        ),
                        @Parameter(
                                name = ApiConstants.PARAM_CAPACIDAD_ID,
                                in = ParameterIn.PATH,
                                description = ApiConstants.PARAM_CAPACIDAD_ID_DESC,
                                required = true
                        )
                },
                responses = {
                        @ApiResponse(responseCode = ApiConstants.HTTP_OK, description = ApiConstants.RESPONSE_GET_200),
                        @ApiResponse(responseCode = ApiConstants.HTTP_BAD_REQUEST, description = ApiConstants.RESPONSE_GET_400),
                        @ApiResponse(responseCode = ApiConstants.HTTP_INTERNAL_ERROR, description = ApiConstants.RESPONSE_GET_500)
                }
        )
)
public @interface CapacidadTecnologiaGetApiDoc {}

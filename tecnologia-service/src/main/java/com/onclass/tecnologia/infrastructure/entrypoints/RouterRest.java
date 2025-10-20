package com.onclass.tecnologia.infrastructure.entrypoints;

import com.onclass.tecnologia.application.configSwagger.*;
import com.onclass.tecnologia.infrastructure.entrypoints.handler.CapacidadTecnologiaHandlerImpl;
import com.onclass.tecnologia.infrastructure.entrypoints.handler.TecnologiaHandlerImpl;
import com.onclass.tecnologia.infrastructure.entrypoints.util.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {

    @Bean
    @TecnologiaApiDoc
    public RouterFunction<ServerResponse> tecnologiaRoutes(TecnologiaHandlerImpl handler) {
        return route()
                .POST(Constants.TECNOLOGIA_PATH, handler::createTecnologia)
                .build();
    }

    @Bean
    @CapacidadTecnologiaApiDoc
    public RouterFunction<ServerResponse> capacidadTecnologiaRoutes(CapacidadTecnologiaHandlerImpl handler) {
        return route()
                .POST(Constants.CAPACIDAD_TECNOLOGIA_PATH, handler::createCapacidadTecnologias)
                .build();
    }

    @Bean
    @CapacidadTecnologiaGetApiDoc
    public RouterFunction<ServerResponse> getTecnologiasByCapacidad(CapacidadTecnologiaHandlerImpl handler) {
        return route(GET(Constants.CAPACIDAD_TECNOLOGIA_PATH + "/{capacidadId}/tecnologias"),
                handler::listTecnologiasByCapacidad);
    }

    @Bean
    @CapacidadTecnologiaDeleteApiDoc
    public RouterFunction<ServerResponse> deleteTecnologiasByCapacidadesRoute(CapacidadTecnologiaHandlerImpl handler) {
        return route()
                .DELETE(Constants.CAPACIDAD_TECNOLOGIA_PATH + "/by-capacidades", handler::deleteTecnologiasByCapacidades)
                .build();
    }

    @Bean
    @CapacidadTecnologiaFindIdsApiDoc
    public RouterFunction<ServerResponse> findTecnologiasIdsByCapacidadesRoute(CapacidadTecnologiaHandlerImpl handler) {
        return route()
                .POST(Constants.CAPACIDAD_TECNOLOGIA_PATH + "/tecnologias/by-capacidades",
                        handler::findTecnologiasIdsByCapacidades)
                .build();
    }

    @Bean
    @TecnologiaDeleteApiDoc
    public RouterFunction<ServerResponse> deleteTecnologiaRoute(TecnologiaHandlerImpl handler) {
        return route()
                .DELETE(Constants.TECNOLOGIA_PATH + "/{id}", handler::deleteTecnologia)
                .build();
    }

    @Bean
    @CapacidadTecnologiaCountByTecnologiaApiDoc
    public RouterFunction<ServerResponse> countCapacidadesByTecnologiaRoute(CapacidadTecnologiaHandlerImpl handler) {
        return route()
                .GET(Constants.CAPACIDAD_TECNOLOGIA_PATH + "/count/by-tecnologia/{tecnologiaId}",
                        handler::countCapacidadesByTecnologiaId)
                .build();
    }
}

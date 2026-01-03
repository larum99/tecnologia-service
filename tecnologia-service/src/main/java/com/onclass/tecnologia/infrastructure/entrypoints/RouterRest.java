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
        return route(GET(Constants.CAPACIDAD_TECNOLOGIA_PATH + Constants.PATH_CAPACIDAD_TECNOLOGIAS),
                handler::listTecnologiasByCapacidad);
    }

    @Bean
    @CapacidadTecnologiaDeleteApiDoc
    public RouterFunction<ServerResponse> deleteTecnologiasByCapacidadesRoute(CapacidadTecnologiaHandlerImpl handler) {
        return route()
                .DELETE(Constants.CAPACIDAD_TECNOLOGIA_PATH + Constants.PATH_BY_CAPACIDADES, handler::deleteTecnologiasByCapacidades)
                .build();
    }

    @Bean
    @CapacidadTecnologiaFindIdsApiDoc
    public RouterFunction<ServerResponse> findTecnologiasIdsByCapacidadesRoute(CapacidadTecnologiaHandlerImpl handler) {
        return route()
                .POST(Constants.CAPACIDAD_TECNOLOGIA_PATH + Constants.PATH_TECNOLOGIAS_BY_CAPACIDADES,
                        handler::findTecnologiasIdsByCapacidades)
                .build();
    }

    @Bean
    @TecnologiaDeleteApiDoc
    public RouterFunction<ServerResponse> deleteTecnologiaRoute(TecnologiaHandlerImpl handler) {
        return route()
                .DELETE(Constants.TECNOLOGIA_PATH + Constants.PATH_ID, handler::deleteTecnologia)
                .build();
    }

    @Bean
    @CapacidadTecnologiaCountByTecnologiaApiDoc
    public RouterFunction<ServerResponse> countCapacidadesByTecnologiaRoute(CapacidadTecnologiaHandlerImpl handler) {
        return route()
                .GET(Constants.CAPACIDAD_TECNOLOGIA_PATH + Constants.PATH_COUNT_BY_TECNOLOGIA,
                        handler::countCapacidadesByTecnologiaId)
                .build();
    }
}

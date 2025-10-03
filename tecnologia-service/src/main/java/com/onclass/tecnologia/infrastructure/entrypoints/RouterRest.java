package com.onclass.tecnologia.infrastructure.entrypoints;

import com.onclass.tecnologia.application.configSwagger.TecnologiaApiDoc;
import com.onclass.tecnologia.infrastructure.entrypoints.handler.TecnologiaHandlerImpl;
import com.onclass.tecnologia.infrastructure.entrypoints.util.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {

    @Bean
    @TecnologiaApiDoc
    public RouterFunction<ServerResponse> tecnologiaRoutes(TecnologiaHandlerImpl handler) {
        return route(POST(Constants.TECNOLOGIA_PATH), handler::createTecnologia);
    }
}

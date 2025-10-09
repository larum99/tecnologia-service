package com.onclass.tecnologia.infrastructure.entrypoints.handler;

import com.onclass.tecnologia.domain.api.CapacidadTecnologiaServicePort;
import com.onclass.tecnologia.domain.enums.TechnicalMessage;
import com.onclass.tecnologia.domain.exceptions.BusinessException;
import com.onclass.tecnologia.domain.exceptions.TechnicalException;
import com.onclass.tecnologia.infrastructure.entrypoints.dto.CapacidadTecnologiaDTO;
import com.onclass.tecnologia.infrastructure.entrypoints.mapper.CapacidadTecnologiaMapper;
import com.onclass.tecnologia.infrastructure.entrypoints.util.APIResponse;
import com.onclass.tecnologia.infrastructure.entrypoints.util.Constants;
import com.onclass.tecnologia.infrastructure.entrypoints.util.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CapacidadTecnologiaHandlerImpl {

    private static final Logger log = LoggerFactory.getLogger(CapacidadTecnologiaHandlerImpl.class);

    private final CapacidadTecnologiaServicePort capacidadTecnologiaServicePort;
    private final CapacidadTecnologiaMapper capacidadTecnologiaMapper;

    public CapacidadTecnologiaHandlerImpl(CapacidadTecnologiaServicePort capacidadTecnologiaServicePort,
                                          CapacidadTecnologiaMapper capacidadTecnologiaMapper) {
        this.capacidadTecnologiaServicePort = capacidadTecnologiaServicePort;
        this.capacidadTecnologiaMapper = capacidadTecnologiaMapper;
    }

    public Mono<ServerResponse> createCapacidadTecnologias(ServerRequest request) {
        String messageId = getMessageId(request);

        return request.bodyToFlux(CapacidadTecnologiaDTO.class)
                .map(capacidadTecnologiaMapper::toModel)
                .collectList()
                .flatMapMany(list -> capacidadTecnologiaServicePort.registrarCapacidadTecnologias(list, messageId))
                .collectList()
                .flatMap(saved -> ServerResponse.status(HttpStatus.CREATED).bodyValue(saved))
                .contextWrite(Context.of(Constants.X_MESSAGE_ID, messageId))
                .doOnError(ex -> log.error(Constants.TECNOLOGIA_ERROR, ex))
                .onErrorResume(ex -> buildErrorResponse(messageId, ex));
    }

    public Mono<ServerResponse> listTecnologiasByCapacidad(ServerRequest request) {
        String messageId = getMessageId(request);
        Long capacidadId = Long.valueOf(request.pathVariable("capacidadId"));

        return capacidadTecnologiaServicePort
                .listarTecnologiasPorCapacidad(capacidadId)
                .collectList()
                .flatMap(list -> ServerResponse.ok().bodyValue(list))
                .contextWrite(Context.of(Constants.X_MESSAGE_ID, messageId))
                .doOnError(ex -> log.error("Error al listar tecnologías para capacidad {}", capacidadId, ex))
                .onErrorResume(ex -> buildErrorResponse(messageId, ex));
    }

    private Mono<ServerResponse> buildErrorResponse(String messageId, Throwable ex) {
        if (ex instanceof BusinessException bex) {
            return buildError(HttpStatus.BAD_REQUEST, messageId, bex.getTechnicalMessage());
        }
        if (ex instanceof TechnicalException tex) {
            return buildError(HttpStatus.INTERNAL_SERVER_ERROR, messageId, tex.getTechnicalMessage());
        }
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, messageId, TechnicalMessage.INTERNAL_ERROR);
    }

    private Mono<ServerResponse> buildError(HttpStatus status, String identifier, TechnicalMessage error) {
        APIResponse apiErrorResponse = APIResponse.builder()
                .code(error.getCode())
                .message(error.getDescription())
                .identifier(identifier)
                .date(Instant.now().toString())
                .errors(List.of(ErrorDTO.builder()
                        .code(error.getCode())
                        .message(error.getDescription())
                        .param(error.getParam())
                        .build()))
                .build();
        return ServerResponse.status(status).bodyValue(apiErrorResponse);
    }

    private String getMessageId(ServerRequest serverRequest) {
        return Optional.ofNullable(serverRequest.headers().firstHeader(Constants.X_MESSAGE_ID))
                .orElse(UUID.randomUUID().toString());
    }
}

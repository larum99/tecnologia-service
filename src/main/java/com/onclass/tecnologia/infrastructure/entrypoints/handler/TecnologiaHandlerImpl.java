package com.onclass.tecnologia.infrastructure.entrypoints.handler;

import com.onclass.tecnologia.domain.api.TecnologiaServicePort;
import com.onclass.tecnologia.domain.enums.TechnicalMessage;
import com.onclass.tecnologia.domain.exceptions.BusinessException;
import com.onclass.tecnologia.domain.exceptions.TechnicalException;
import com.onclass.tecnologia.infrastructure.entrypoints.dto.TecnologiaDTO;
import com.onclass.tecnologia.infrastructure.entrypoints.mapper.TecnologiaMapper;
import com.onclass.tecnologia.infrastructure.entrypoints.util.APIResponse;
import com.onclass.tecnologia.infrastructure.entrypoints.util.Constants;
import com.onclass.tecnologia.infrastructure.entrypoints.util.ErrorDTO;
import com.onclass.tecnologia.infrastructure.entrypoints.util.HandlerConstants;
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
public class TecnologiaHandlerImpl {

    private static final Logger log = LoggerFactory.getLogger(TecnologiaHandlerImpl.class);

    private final TecnologiaServicePort tecnologiaServicePort;
    private final TecnologiaMapper tecnologiaMapper;

    public TecnologiaHandlerImpl(TecnologiaServicePort tecnologiaServicePort,
                                 TecnologiaMapper tecnologiaMapper) {
        this.tecnologiaServicePort = tecnologiaServicePort;
        this.tecnologiaMapper = tecnologiaMapper;
    }

    public Mono<ServerResponse> createTecnologia(ServerRequest request) {
        String messageId = getMessageId(request);

        return request.bodyToMono(TecnologiaDTO.class)
                .flatMap(dto -> tecnologiaServicePort
                        .registrarTecnologia(tecnologiaMapper.toModel(dto), messageId)
                        .doOnSuccess(saved -> log.info(HandlerConstants.LOG_TECNOLOGIA_CREATED, messageId))
                )
                .flatMap(saved -> {
                    APIResponse successResponse = APIResponse.builder()
                            .code(TechnicalMessage.TECNOLOGIA_CREATED.getCode())
                            .message(TechnicalMessage.TECNOLOGIA_CREATED.getDescription())
                            .identifier(messageId)
                            .date(Instant.now().toString())
                            .data(tecnologiaMapper.toDTO(saved))
                            .build();
                    return ServerResponse
                            .status(HttpStatus.CREATED)
                            .bodyValue(successResponse);
                })
                .contextWrite(Context.of(Constants.X_MESSAGE_ID, messageId))
                .doOnError(ex -> log.error(HandlerConstants.TECNOLOGIA_ERROR, ex))
                .onErrorResume(ex -> buildErrorResponse(messageId, ex));
    }

    public Mono<ServerResponse> deleteTecnologia(ServerRequest request) {
        String messageId = getMessageId(request);
        Long tecnologiaId = Long.valueOf(request.pathVariable(HandlerConstants.PATH_VARIABLE_ID));

        return tecnologiaServicePort.eliminarTecnologia(tecnologiaId, messageId)
                .then(ServerResponse.noContent().build())
                .contextWrite(Context.of(Constants.X_MESSAGE_ID, messageId))
                .doOnError(ex -> log.error(HandlerConstants.LOG_ERROR_DELETE_TECNOLOGIA, tecnologiaId, ex))
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
        return Optional.ofNullable(serverRequest.headers().firstHeader(HandlerConstants.HEADER_X_MESSAGE_ID))
                .orElse(UUID.randomUUID().toString());
    }
}

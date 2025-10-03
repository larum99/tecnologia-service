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

    /**
     * Registrar una nueva tecnología.
     * Valida nombre y descripción antes de llamar al service.
     */
    public Mono<ServerResponse> createTecnologia(ServerRequest request) {
        String messageId = getMessageId(request);

        return request.bodyToMono(TecnologiaDTO.class)
                .flatMap(dto -> tecnologiaServicePort
                        .registrarTecnologia(tecnologiaMapper.toModel(dto), messageId)
                        .doOnSuccess(saved -> log.info("Tecnología creada con messageId: {}", messageId))
                )
                .flatMap(saved -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .bodyValue(TechnicalMessage.TECNOLOGIA_CREATED.getDescription()))
                .contextWrite(Context.of(Constants.X_MESSAGE_ID, messageId))
                .doOnError(ex -> log.error(Constants.TECNOLOGIA_ERROR, ex))
                .onErrorResume(BusinessException.class, ex -> buildErrorResponse(
                        HttpStatus.BAD_REQUEST,
                        messageId,
                        ex.getTechnicalMessage(),
                        List.of(ErrorDTO.builder()
                                .code(ex.getTechnicalMessage().getCode())
                                .message(ex.getTechnicalMessage().getDescription())
                                .param(ex.getTechnicalMessage().getParam())
                                .build())
                ))
                .onErrorResume(TechnicalException.class, ex -> buildErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        messageId,
                        ex.getTechnicalMessage(),
                        List.of(ErrorDTO.builder()
                                .code(ex.getTechnicalMessage().getCode())
                                .message(ex.getTechnicalMessage().getDescription())
                                .param(ex.getTechnicalMessage().getParam())
                                .build())
                ))
                .onErrorResume(ex -> {
                    log.error("Unexpected error with messageId: {}", messageId, ex);
                    return buildErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR,
                            messageId,
                            TechnicalMessage.INTERNAL_ERROR,
                            List.of(ErrorDTO.builder()
                                    .code(TechnicalMessage.INTERNAL_ERROR.getCode())
                                    .message(TechnicalMessage.INTERNAL_ERROR.getDescription())
                                    .build())
                    );
                });
    }

    private Mono<ServerResponse> buildErrorResponse(HttpStatus httpStatus, String identifier,
                                                    TechnicalMessage error, List<ErrorDTO> errors) {
        APIResponse apiErrorResponse = APIResponse.builder()
                .code(error.getCode())
                .message(error.getDescription())
                .identifier(identifier)
                .date(Instant.now().toString())
                .errors(errors)
                .build();
        return ServerResponse.status(httpStatus).bodyValue(apiErrorResponse);
    }

    /**
     * Obtiene el messageId de los headers del request.
     */
    private String getMessageId(ServerRequest serverRequest) {
        return serverRequest.headers().firstHeader(Constants.X_MESSAGE_ID);
    }
}

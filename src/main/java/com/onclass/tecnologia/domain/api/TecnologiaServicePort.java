package com.onclass.tecnologia.domain.api;

import com.onclass.tecnologia.domain.model.Tecnologia;
import reactor.core.publisher.Mono;

public interface TecnologiaServicePort {
    Mono<Tecnologia> registrarTecnologia(Tecnologia tecnologia, String messageId);
    Mono<Void> eliminarTecnologia(Long tecnologiaId, String messageId);
}

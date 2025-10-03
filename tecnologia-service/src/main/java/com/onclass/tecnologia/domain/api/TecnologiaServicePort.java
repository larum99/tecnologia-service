package com.onclass.tecnologia.domain.api;

import com.onclass.tecnologia.domain.model.Tecnologia;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TecnologiaServicePort {
    Mono<Tecnologia> registrarTecnologia(Tecnologia tecnologia, String messageId);
}

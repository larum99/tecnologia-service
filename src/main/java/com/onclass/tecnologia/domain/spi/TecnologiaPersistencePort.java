package com.onclass.tecnologia.domain.spi;

import com.onclass.tecnologia.domain.model.Tecnologia;
import reactor.core.publisher.Mono;

public interface TecnologiaPersistencePort {
    Mono<Tecnologia> saveTecnologia(Tecnologia tecnologia);
    Mono<Boolean> existByNombre(String nombre);
    Mono<Void> deleteById(Long tecnologiaId);
    Mono<Boolean> existsById(Long tecnologiaId);
}

package com.onclass.tecnologia.domain.spi;

import com.onclass.tecnologia.domain.model.Tecnologia;
import reactor.core.publisher.Mono;

public interface TecnologiaPersistencePort {
    Mono<Tecnologia> save(Tecnologia tecnologia);
    Mono<Boolean> existByNombre(String nombre);
}

package com.onclass.tecnologia.domain.spi;

import com.onclass.tecnologia.domain.model.CapacidadTecnologia;
import reactor.core.publisher.Mono;

public interface CapacidadTecnologiaPersistencePort {
    Mono<CapacidadTecnologia> save(CapacidadTecnologia relacion);
}

package com.onclass.tecnologia.domain.spi;

import com.onclass.tecnologia.domain.model.CapacidadTecnologia;
import com.onclass.tecnologia.domain.model.Tecnologia;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CapacidadTecnologiaPersistencePort {

    Mono<CapacidadTecnologia> saveCapacidadTecnologia(CapacidadTecnologia relacion);

    Flux<Tecnologia> findTecnologiasByCapacidadId(Long capacidadId);
}

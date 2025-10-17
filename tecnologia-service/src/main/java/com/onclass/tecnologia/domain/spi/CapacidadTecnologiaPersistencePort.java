package com.onclass.tecnologia.domain.spi;

import com.onclass.tecnologia.domain.model.CapacidadTecnologia;
import com.onclass.tecnologia.domain.model.Tecnologia;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CapacidadTecnologiaPersistencePort {

    Mono<CapacidadTecnologia> saveCapacidadTecnologia(CapacidadTecnologia relacion);
    Flux<Tecnologia> findTecnologiasByCapacidadId(Long capacidadId);
    Mono<Void> deleteByCapacidadIds(List<Long> capacidadIds);
    Flux<Long> findTecnologiaIdsByCapacidades(List<Long> capacidadIds);
    Mono<Long> countCapacidadesByTecnologiaId(Long tecnologiaId);
}

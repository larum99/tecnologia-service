package com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.repository;

import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.entity.CapacidadTecnologiaEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CapacidadTecnologiaRepository extends ReactiveCrudRepository<CapacidadTecnologiaEntity, Long> {
    Flux<CapacidadTecnologiaEntity> findByCapacidadId(Long capacidadId);
    Mono<Long> countByCapacidadId(Long capacidadId);
}


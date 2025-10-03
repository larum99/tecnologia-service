package com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.repository;

import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.entity.TecnologiaEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TecnologiaRepository extends ReactiveCrudRepository<TecnologiaEntity, Long> {
    Mono<TecnologiaEntity> findByNombre(String nombre);
}

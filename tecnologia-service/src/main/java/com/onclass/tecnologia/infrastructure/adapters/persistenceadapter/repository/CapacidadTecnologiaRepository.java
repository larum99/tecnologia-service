package com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.repository;

import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.entity.CapacidadTecnologiaEntity;
import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.entity.TecnologiaEntity;
import com.onclass.tecnologia.infrastructure.entrypoints.dto.TecnologiaSummaryDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CapacidadTecnologiaRepository extends ReactiveCrudRepository<CapacidadTecnologiaEntity, Long> {

    Flux<CapacidadTecnologiaEntity> findByCapacidadId(Long capacidadId);
    Mono<Long> countByCapacidadId(Long capacidadId);

    @Query("""
        SELECT t.id, t.nombre, t.descripcion
        FROM capacidad_tecnologias ct
        JOIN tecnologias t ON ct.id_tecnologia = t.id
        WHERE ct.id_capacidad = :capacidadId
    """)
    Flux<TecnologiaEntity> findTecnologiasByCapacidadId(Long capacidadId);
}



package com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.repository;

import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.entity.CapacidadTecnologiaEntity;
import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.entity.TecnologiaEntity;
import com.onclass.tecnologia.infrastructure.entrypoints.dto.TecnologiaSummaryDTO;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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

    @Modifying
    @Query("DELETE FROM capacidad_tecnologias WHERE id_capacidad IN (:capacidadIds)")
    Mono<Void> deleteByCapacidadIds(List<Long> capacidadIds);

    @Query("""
        SELECT DISTINCT id_tecnologia
        FROM capacidad_tecnologias
        WHERE id_capacidad IN (:capacidadIds)
    """)
    Flux<Long> findTecnologiaIdsByCapacidades(List<Long> capacidadIds);

    @Query("""
        SELECT COUNT(*) 
        FROM capacidad_tecnologias 
        WHERE id_tecnologia = :tecnologiaId
    """)
    Mono<Long> countCapacidadesByTecnologiaId(Long tecnologiaId);
}



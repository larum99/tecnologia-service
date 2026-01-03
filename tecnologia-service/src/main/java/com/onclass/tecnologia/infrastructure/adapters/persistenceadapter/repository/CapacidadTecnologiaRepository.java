package com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.repository;

import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.entity.CapacidadTecnologiaEntity;
import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.entity.TecnologiaEntity;
import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.util.RepositoryConstants;
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

    @Query(RepositoryConstants.QUERY_FIND_TECNOLOGIAS_BY_CAPACIDAD_ID)
    Flux<TecnologiaEntity> findTecnologiasByCapacidadId(Long capacidadId);

    @Modifying
    @Query(RepositoryConstants.QUERY_DELETE_BY_CAPACIDAD_IDS)
    Mono<Void> deleteByCapacidadIds(List<Long> capacidadIds);

    @Query(RepositoryConstants.QUERY_FIND_TECNOLOGIA_IDS_BY_CAPACIDADES)
    Flux<Long> findTecnologiaIdsByCapacidades(List<Long> capacidadIds);

    @Query(RepositoryConstants.QUERY_COUNT_CAPACIDADES_BY_TECNOLOGIA_ID)
    Mono<Long> countCapacidadesByTecnologiaId(Long tecnologiaId);
}



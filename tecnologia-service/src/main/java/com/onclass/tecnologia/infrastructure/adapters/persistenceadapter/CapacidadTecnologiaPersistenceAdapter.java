package com.onclass.tecnologia.infrastructure.adapters.persistenceadapter;

import com.onclass.tecnologia.domain.model.CapacidadTecnologia;
import com.onclass.tecnologia.domain.spi.CapacidadTecnologiaPersistencePort;
import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.mapper.CapacidadTecnologiaEntityMapper;
import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.repository.CapacidadTecnologiaRepository;
import com.onclass.tecnologia.infrastructure.entrypoints.dto.TecnologiaSummaryDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CapacidadTecnologiaPersistenceAdapter implements CapacidadTecnologiaPersistencePort {

    private final CapacidadTecnologiaRepository repository;
    private final CapacidadTecnologiaEntityMapper mapper;

    public CapacidadTecnologiaPersistenceAdapter(CapacidadTecnologiaRepository repository,
                                                 CapacidadTecnologiaEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Mono<CapacidadTecnologia> saveCapacidadTecnologia(CapacidadTecnologia relacion) {
        return repository.save(mapper.toEntity(relacion))
                .map(mapper::toModel);
    }

    @Override
    public Flux<TecnologiaSummaryDTO> findTecnologiasByCapacidadId(Long capacidadId) {
        return repository.findTecnologiasConNombreByCapacidadId(capacidadId);
    }
}

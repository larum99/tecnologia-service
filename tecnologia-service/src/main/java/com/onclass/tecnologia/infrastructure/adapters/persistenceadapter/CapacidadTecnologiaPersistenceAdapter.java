package com.onclass.tecnologia.infrastructure.adapters.persistenceadapter;

import com.onclass.tecnologia.domain.model.CapacidadTecnologia;
import com.onclass.tecnologia.domain.model.Tecnologia;
import com.onclass.tecnologia.domain.spi.CapacidadTecnologiaPersistencePort;
import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.mapper.CapacidadTecnologiaEntityMapper;
import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.mapper.TecnologiaEntityMapper;
import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.repository.CapacidadTecnologiaRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CapacidadTecnologiaPersistenceAdapter implements CapacidadTecnologiaPersistencePort {

    private final CapacidadTecnologiaRepository repository;
    private final CapacidadTecnologiaEntityMapper capacidadMapper;
    private final TecnologiaEntityMapper tecnologiaMapper;

    public CapacidadTecnologiaPersistenceAdapter(
            CapacidadTecnologiaRepository repository,
            CapacidadTecnologiaEntityMapper capacidadMapper,
            TecnologiaEntityMapper tecnologiaMapper
    ) {
        this.repository = repository;
        this.capacidadMapper = capacidadMapper;
        this.tecnologiaMapper = tecnologiaMapper;
    }

    @Override
    public Mono<CapacidadTecnologia> saveCapacidadTecnologia(CapacidadTecnologia relacion) {
        return repository.save(capacidadMapper.toEntity(relacion))
                .map(capacidadMapper::toModel);
    }

    @Override
    public Flux<Tecnologia> findTecnologiasByCapacidadId(Long capacidadId) {
        return repository.findTecnologiasByCapacidadId(capacidadId)
                .map(tecnologiaMapper::toModel); // ahora funciona
    }

}

package com.onclass.tecnologia.infrastructure.adapters.persistenceadapter;

import com.onclass.tecnologia.domain.model.Tecnologia;
import com.onclass.tecnologia.domain.spi.TecnologiaPersistencePort;
import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.mapper.TecnologiaEntityMapper;
import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.repository.TecnologiaRepository;
import reactor.core.publisher.Mono;

public class TecnologiaPersistenceAdapter implements TecnologiaPersistencePort {

    private final TecnologiaRepository tecnologiaRepository;
    private final TecnologiaEntityMapper tecnologiaEntityMapper;

    public TecnologiaPersistenceAdapter(TecnologiaRepository tecnologiaRepository,
                                        TecnologiaEntityMapper tecnologiaEntityMapper) {
        this.tecnologiaRepository = tecnologiaRepository;
        this.tecnologiaEntityMapper = tecnologiaEntityMapper;
    }

    @Override
    public Mono<Tecnologia> save(Tecnologia tecnologia) {
        return tecnologiaRepository.save(tecnologiaEntityMapper.toEntity(tecnologia))
                .map(tecnologiaEntityMapper::toModel);
    }

    @Override
    public Mono<Boolean> existByNombre(String nombre) {
        return tecnologiaRepository.findByNombre(nombre)
                .map(tecnologiaEntityMapper::toModel)
                .map(t -> true)
                .defaultIfEmpty(false);
    }
}

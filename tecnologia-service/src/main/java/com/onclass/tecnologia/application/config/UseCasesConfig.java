package com.onclass.tecnologia.application.config;

import com.onclass.tecnologia.domain.api.CapacidadTecnologiaServicePort;
import com.onclass.tecnologia.domain.api.TecnologiaServicePort;
import com.onclass.tecnologia.domain.spi.CapacidadTecnologiaPersistencePort;
import com.onclass.tecnologia.domain.spi.TecnologiaPersistencePort;
import com.onclass.tecnologia.domain.usecase.CapacidadTecnologiaUseCase;
import com.onclass.tecnologia.domain.usecase.TecnologiaUseCase;
import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.CapacidadTecnologiaPersistenceAdapter;
import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.TecnologiaPersistenceAdapter;
import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.mapper.CapacidadTecnologiaEntityMapper;
import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.mapper.TecnologiaEntityMapper;
import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.repository.CapacidadTecnologiaRepository;
import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.repository.TecnologiaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    @Bean
    public TecnologiaPersistencePort tecnologiaPersistencePort(
            TecnologiaRepository tecnologiaRepository,
            TecnologiaEntityMapper tecnologiaEntityMapper) {
        return new TecnologiaPersistenceAdapter(tecnologiaRepository, tecnologiaEntityMapper);
    }

    @Bean
    public TecnologiaServicePort tecnologiaServicePort(TecnologiaPersistencePort tecnologiaPersistencePort) {
        return new TecnologiaUseCase(tecnologiaPersistencePort);
    }

    @Bean
    public CapacidadTecnologiaPersistencePort capacidadTecnologiaPersistencePort(
            CapacidadTecnologiaRepository repository,
            CapacidadTecnologiaEntityMapper mapper) {
        return new CapacidadTecnologiaPersistenceAdapter(repository, mapper);
    }

    @Bean
    public CapacidadTecnologiaServicePort capacidadTecnologiaServicePort(
            CapacidadTecnologiaPersistencePort persistencePort) {
        return new CapacidadTecnologiaUseCase(persistencePort);
    }

}


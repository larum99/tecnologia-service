package com.onclass.tecnologia.domain.usecase;

import com.onclass.tecnologia.domain.api.CapacidadTecnologiaServicePort;
import com.onclass.tecnologia.domain.model.CapacidadTecnologia;
import com.onclass.tecnologia.domain.model.Tecnologia;
import com.onclass.tecnologia.domain.spi.CapacidadTecnologiaPersistencePort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CapacidadTecnologiaUseCase implements CapacidadTecnologiaServicePort {

    private final CapacidadTecnologiaPersistencePort persistencePort;

    public CapacidadTecnologiaUseCase(CapacidadTecnologiaPersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public Flux<CapacidadTecnologia> registrarCapacidadTecnologias(List<CapacidadTecnologia> relaciones, String messageId) {
        return Flux.fromIterable(relaciones)
                .flatMap(persistencePort::saveCapacidadTecnologia);
    }

    @Override
    public Flux<Tecnologia> listarTecnologiasPorCapacidad(Long capacidadId) {
        return persistencePort.findTecnologiasByCapacidadId(capacidadId);
    }
}
